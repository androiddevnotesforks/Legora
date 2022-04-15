package com.apartments.api.service.base

import com.apartments.api.models.BaseEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface ServiceImplementation<T : BaseEntity, Repo: PagingAndSortingRepository<T, Long>> {

    fun createEntity(reqBody: T, fields: Array<String>?): Any?

    fun getEntityById(id: Long, fields: Array<String>?): Any?

    fun getAll(pageNumber: Int, perPage: Int, fields: Array<String>?): Page<T>

    fun getAllEnabledEntities(pageNumber: Int, perPage: Int, fields: Array<String>?): Page<T>

    fun getAllDisabledEntities(pageNumber: Int, perPage: Int, fields: Array<String>?): Page<T>

    fun deleteEntityById(id: Long): Boolean

    fun deleteAllEntities()

    fun updateEntity(reqBody: T): Any?

    fun getRepository(): Repo?

    fun preCheckAttachedId(id: Long): Boolean

    fun getPage(pageNumber: Int, perPage: Int): Pageable

    fun isStringLengthValid(value: String?, minLength: Int, maxLength: Int): Boolean

    fun isStringEmpty(value: String?): Boolean

    fun isNumberProvided(value: Int): Boolean

}