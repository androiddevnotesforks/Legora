package com.apartments.api.models.repos

import com.apartments.api.models.entities.place.Place
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository: PagingAndSortingRepository<Place, Long> {

    fun findAllByIsEnabled(isEnabled: Boolean, pageable: Pageable): Page<Place>

}
