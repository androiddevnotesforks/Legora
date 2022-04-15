package com.apartments.api.models.repos

import com.apartments.api.models.entities.account.Account
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

interface AccountsRepository: PagingAndSortingRepository<Account, Long> {

    fun findAccountByEmail(email: String): Account?

    fun findAccountByPhoneNumber(phoneNumber: String): Account?

    fun findAllByUserEnabled(isEnabled: Boolean, pageable: Pageable): Page<Account>

    fun findByUserName(userName: String): Optional<Account>

}
