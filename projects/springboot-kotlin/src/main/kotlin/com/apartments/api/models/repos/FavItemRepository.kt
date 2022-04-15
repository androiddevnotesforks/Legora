package com.apartments.api.models.repos

import com.apartments.api.models.entities.FavItem
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface FavItemRepository: PagingAndSortingRepository<FavItem, Long> {
}