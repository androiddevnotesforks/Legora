package #{PackageName}.controllers.base

import #{PackageName}.errors.EntityNotFoundException
import #{PackageName}.errors.InvalidDataException
import #{PackageName}.models.BaseEntity
import #{PackageName}.response.BaseResponse
import #{PackageName}.service.base.BaseService
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest


interface BaseControllerImplementation<E : BaseEntity, Repo : PagingAndSortingRepository<E, Long>, S : BaseService<E, Repo>?> {

    @Throws(InvalidDataException::class)
    fun save(request: HttpServletRequest?, entity: E, fields: String?): ResponseEntity<BaseResponse?>?

    @Throws(InvalidDataException::class, EntityNotFoundException::class)
    fun getById(request: HttpServletRequest?, id: Long?, fields: String?): ResponseEntity<BaseResponse?>?

    @Throws(InvalidDataException::class, EntityNotFoundException::class)
    fun getAll(request: HttpServletRequest?, filter: String?, pageable: Pageable?, fields: String?): ResponseEntity<BaseResponse?>?

    @Throws(InvalidDataException::class, EntityNotFoundException::class)
    fun getAllEntities(request: HttpServletRequest?, filter: String?, pageable: Pageable?, fields: String?): ResponseEntity<BaseResponse?>?

    fun deleteById(request: HttpServletRequest?, id: Long?): ResponseEntity<BaseResponse?>?

    fun deleteAll(request: HttpServletRequest?): ResponseEntity<BaseResponse?>?

    fun getService(): S

}