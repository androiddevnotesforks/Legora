package #{PackageName}.service.base

import #{PackageName}.errors.EntityNotFoundException
import #{PackageName}.errors.InvalidDataException
import #{PackageName}.errors.UnknownErrorException
import #{PackageName}.models.BaseEntity
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

abstract class BaseService<T : BaseEntity, Repo : PagingAndSortingRepository<T, Long>> : ServiceImplementation<T, Repo> {

    override fun createEntity(reqBody: T, fields: Array<String>?): Any? {
        getRepository()?.save(reqBody)
        return reqBody
    }

    override fun updateEntity(reqBody: T): Any? {
        getRepository()?.save(reqBody)
        return reqBody
    }

    @Throws(EntityNotFoundException::class)
    override fun getAll(pageNumber: Int, perPage: Int, fields: Array<String>?): Page<T> {
        try {
            return getRepository()?.findAll(getPage(pageNumber, perPage)) ?: Page.empty()
        } catch (ex: EmptyResultDataAccessException) {
            throw EntityNotFoundException("Data Not Found", ex.message)
        }
    }

    @Throws(EntityNotFoundException::class)
    override fun getEntityById(id: Long, fields: Array<String>?): Any? {
        preCheckAttachedId(id)
        try {
            val account = getRepository()?.findById(id)?.get()
            if (account == null) {
                throw EntityNotFoundException("Data Not Found", null)
            } else {
                return account
            }
        } catch (ex: NoSuchElementException) {
            throw EntityNotFoundException("Data Not Found", ex.message)
        }
    }

    @Throws(UnknownErrorException::class)
    override fun deleteAllEntities() {
        try {
            getRepository()?.deleteAll()
        } catch (ex: Exception) {
            throw UnknownErrorException(ex.message, null)
        }
    }

    @Throws(InvalidDataException::class)
    override fun deleteEntityById(id: Long): Boolean {
        preCheckAttachedId(id)
        getRepository()?.deleteById(id)
        return true
    }

    @Throws(InvalidDataException::class)
    override fun preCheckAttachedId(id: Long): Boolean {
        if (id <= 0) {
            throw InvalidDataException("Invalid Input (id)", id)
        } else {
            return true
        }
    }

    override fun getPage(pageNumber: Int, perPage: Int): Pageable {
        return Pageable.ofSize(perPage).withPage(pageNumber)
    }

    override fun isNumberProvided(value: Int): Boolean {
        return value > 0
    }

    override fun isStringLengthValid(value: String?, minLength: Int, maxLength: Int): Boolean {
        if (isStringEmpty(value)) {
            return false
        }

        val currentValue = value ?: ""
        return currentValue.length in minLength until maxLength
    }

    override fun isStringEmpty(value: String?): Boolean {
        if (value == null) {
            return true
        }

        if (value.isNullOrEmpty()) {
            return true
        }

        return false
    }

}