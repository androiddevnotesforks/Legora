package com.apartments.api.models.repos

import com.apartments.api.models.entities.place.PlaceLocation
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlaceLocationRepository: PagingAndSortingRepository<PlaceLocation, Long> {

    fun findAllByPlaceId(placeId: Long): ArrayList<PlaceLocation>

}
