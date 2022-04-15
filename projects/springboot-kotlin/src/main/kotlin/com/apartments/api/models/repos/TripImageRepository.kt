package com.apartments.api.models.repos

import com.apartments.api.models.entities.trip.TripImage
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TripImageRepository: PagingAndSortingRepository<TripImage, Long> {

    fun findAllByTripId(tripId: Long): ArrayList<TripImage>

}
