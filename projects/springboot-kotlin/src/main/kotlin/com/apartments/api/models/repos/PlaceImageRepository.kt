package com.apartments.api.models.repos

import com.apartments.api.models.entities.place.PlaceImage
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlaceImageRepository: PagingAndSortingRepository<PlaceImage, Long> {

    fun findAllByPlaceId(placeId: Long): ArrayList<PlaceImage>

}