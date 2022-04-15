package com.apartments.api.models.repos

import com.apartments.api.models.entities.logging.RequestsLogging
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RequestInterceptorRepository: CrudRepository<RequestsLogging, Long> {
}