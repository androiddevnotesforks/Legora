package #{PackageName}.controllers.base

import #{PackageName}.models.BaseEntity
import #{PackageName}.response.BaseResponse
import #{PackageName}.response.ResponseGenerator
import #{PackageName}.response.SuccessResponse
import #{PackageName}.service.base.BaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

abstract class BaseController<E : BaseEntity, Repo : PagingAndSortingRepository<E, Long>, S : BaseService<E, Repo>?> : BaseControllerImplementation<E, Repo, S> {

    @ResponseBody
    @RequestMapping(method = [RequestMethod.POST], value = [""])
    override fun save(request: HttpServletRequest?, @RequestBody entity: E, @RequestParam("fields") fields: String?): ResponseEntity<BaseResponse?>? {
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse(HttpStatus.CREATED.value(), "Data Saved", true, getService()?.createEntity(entity, getFields(fields))))
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.GET], value = ["/filter"])
    override fun getAll(request: HttpServletRequest?, @RequestParam("type") filter: String?, pageable: Pageable?, @RequestParam("fields") fields: String?): ResponseEntity<BaseResponse?>? {
        val pageNumber = pageable?.pageNumber ?: 0
        val perPage = pageable?.pageSize ?: 40
        val filteredFields = getFields(fields)
        return when (filter) {
            "all" -> {
                val items: Page<E>? = getService()?.getAll(pageNumber, perPage, filteredFields)
                ResponseEntity.status(HttpStatus.OK).body(ResponseGenerator.getPaginationResponse(request, items))
            }
            "enabled" -> {
                val items: Page<E>? = getService()?.getAllEnabledEntities(pageNumber, perPage, filteredFields)
                ResponseEntity.status(HttpStatus.OK).body(ResponseGenerator.getPaginationResponse(request, items))
            }
            else -> {
                val items: Page<E>? = getService()?.getAllDisabledEntities(pageNumber, perPage, filteredFields)
                ResponseEntity.status(HttpStatus.OK).body(ResponseGenerator.getPaginationResponse(request, items))
            }
        }
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.GET], value = ["/all"])
    override fun getAllEntities(request: HttpServletRequest?, filter: String?, pageable: Pageable?, @RequestParam("fields") fields: String?): ResponseEntity<BaseResponse?>? {
        val pageNumber = pageable?.pageNumber ?: 0
        val perPage = pageable?.pageSize ?: 40
        val filteredFields = getFields(fields)
        val items: Page<E>? = getService()?.getAll(pageNumber, perPage, filteredFields)
        return ResponseEntity.status(HttpStatus.OK).body(ResponseGenerator.getPaginationResponse(request, items))
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.GET], value = ["/{id}"])
    override fun getById(request: HttpServletRequest?, @PathVariable("id") id: Long?, @RequestParam("fields") fields: String?): ResponseEntity<BaseResponse?>? {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse(HttpStatus.OK.value(), "Data Found", true, getService()?.getEntityById(id ?: 0, getFields(fields))))
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.DELETE], value = ["/all"])
    override fun deleteAll(request: HttpServletRequest?): ResponseEntity<BaseResponse?>? {
        getService()?.deleteAllEntities()
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse(HttpStatus.OK.value(), "Data Deleted", true, null))
    }

    @ResponseBody
    @RequestMapping(method = [RequestMethod.DELETE], value = ["/{id}"])
    override fun deleteById(request: HttpServletRequest?, @PathVariable("id") id: Long?): ResponseEntity<BaseResponse?>? {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse(HttpStatus.OK.value(), "Data Deleted", true, getService()?.deleteEntityById(id ?: 0)))
    }

    protected fun getFields(fields: String?): Array<String>? {
        return fields?.split(",")?.toTypedArray()
    }

}