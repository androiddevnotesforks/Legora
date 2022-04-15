package com.apartments.api.models.repos

import com.apartments.api.models.entities.Country
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : PagingAndSortingRepository<Country, Long> {

    fun findAllByIsEnabled(isEnabled: Boolean, pageable: Pageable): Page<Country>

}