package #{PackageName}.controllers

import #{PackageName}.ApiConsts
import #{PackageName}.autowired
import #{PackageName}.controllers.base.BaseController
import #{PackageName}.models.entities.account.Account
import #{PackageName}.models.repos.AccountsRepository
import #{PackageName}.models.req.OtpRequestBody
import #{PackageName}.models.req.VerifyOtpBody
import #{PackageName}.response.BaseResponse
import #{PackageName}.response.ListResponse
import #{PackageName}.response.SuccessResponse
import #{PackageName}.service.AccountsService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(AccountsController.ROUTER_NAME)
class AccountsController : BaseController<Account, AccountsRepository, AccountsService>() {

    private val accountsService: AccountsService by autowired()
    override fun getService(): AccountsService = accountsService
    companion object {
        const val ROUTER_NAME = ApiConsts.VERSION + "accounts"
    }

    override fun getAll(
        request: HttpServletRequest?,
        filter: String?,
        pageable: Pageable?,
        fields: String?
    ): ResponseEntity<BaseResponse?>? {
        val response = super.getAll(request, filter, pageable, fields)
        return getAccountsResponse(response, fields)
    }

    override fun getAllEntities(
        request: HttpServletRequest?,
        filter: String?,
        pageable: Pageable?,
        fields: String?
    ): ResponseEntity<BaseResponse?>? {
        val response = super.getAllEntities(request, filter, pageable, fields)
        return getAccountsResponse(response, fields)
    }

    private fun getAccountsResponse(response: ResponseEntity<BaseResponse?>?, fields: String?): ResponseEntity<BaseResponse?>? {
        val accountsItems = (response?.body as? ListResponse)?.data as? List<Account>
        val filteredItems = ArrayList<HashMap<String, Any?>>()
        if (accountsItems != null) {
            for (account in accountsItems) {
                filteredItems.add(account.getObjectByFields(getFields(fields)))
            }
        }
        (response?.body as? ListResponse)?.data = filteredItems
        return response
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.POST], value = ["/send/otp"])
    fun sendOtp(request: HttpServletRequest?, @RequestBody entity: OtpRequestBody?): ResponseEntity<BaseResponse?>? {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse(HttpStatus.OK.value(), "Operation Successfully", true, getService()?.sendOtp(entity)))
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.POST], value = ["/verify"])
    fun verifyOtp(request: HttpServletRequest?, @RequestBody entity: VerifyOtpBody?): ResponseEntity<BaseResponse?>? {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse(HttpStatus.OK.value(), "Operation Successfully", true, getService()?.verifyOtp(entity)))
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.GET], value = ["/fields"])
    fun getAccountFields(request: HttpServletRequest?): ResponseEntity<BaseResponse?>? {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse(HttpStatus.OK.value(), "Data Found", true, Account.getFields()))
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.POST], value = ["/generate"])
    fun generateAccounts(): ResponseEntity<BaseResponse?>? {
        getService().generateAccounts()
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse(HttpStatus.OK.value(), "Data Created", true, true))
    }

}
