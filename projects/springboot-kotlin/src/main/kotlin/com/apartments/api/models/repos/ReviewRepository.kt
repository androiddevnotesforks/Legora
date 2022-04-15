package com.apartments.api.models.repos

import com.apartments.api.models.entities.Test
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReviewRepository: PagingAndSortingRepository<Test, Long> {

    fun findAllByEntityId(entityId: Long): ArrayList<Test>

}