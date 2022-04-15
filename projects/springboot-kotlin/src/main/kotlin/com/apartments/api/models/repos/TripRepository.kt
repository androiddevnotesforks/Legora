package com.apartments.api.models.repos

import com.apartments.api.models.entities.trip.Trip
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TripRepository: PagingAndSortingRepository<Trip, Long> {

    fun findAllByIsEnabled(isEnabled: Boolean, pageable: Pageable): Page<Trip>

}

