package com.apartments.api.models.repos

import com.apartments.api.models.entities.trip.TripLocation
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TripLocationRepository: PagingAndSortingRepository<TripLocation, Long> {

    fun findAllByTripId(tripId: Long): ArrayList<TripLocation>

}
