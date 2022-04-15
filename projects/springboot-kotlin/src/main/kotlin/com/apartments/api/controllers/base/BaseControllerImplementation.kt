package com.apartments.api.controllers.base

import com.apartments.api.errors.EntityNotFoundException
import com.apartments.api.errors.InvalidDataException
import com.apartments.api.models.BaseEntity
import com.apartments.api.response.BaseResponse
import com.apartments.api.service.base.BaseService
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