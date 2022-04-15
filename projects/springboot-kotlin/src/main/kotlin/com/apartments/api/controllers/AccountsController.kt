package com.apartments.api.controllers

import com.apartments.api.ApiConsts
import com.apartments.api.autowired
import com.apartments.api.controllers.base.BaseController
import com.apartments.api.models.entities.account.Account
import com.apartments.api.models.repos.AccountsRepository
import com.apartments.api.models.req.OtpRequestBody
import com.apartments.api.models.req.VerifyOtpBody
import com.apartments.api.response.BaseResponse
import com.apartments.api.response.ListResponse
import com.apartments.api.response.SuccessResponse
import com.apartments.api.service.AccountsService
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
