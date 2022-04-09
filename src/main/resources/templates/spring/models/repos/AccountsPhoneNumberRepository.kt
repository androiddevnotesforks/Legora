package #{PackageName}.models.repos

import #{PackageName}.models.entities.account.AccountsPhoneNumber
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountsPhoneNumberRepository: CrudRepository<AccountsPhoneNumber, Long> {

    fun findAllByUserId(userId: Long): Optional<ArrayList<AccountsPhoneNumber?>>

    fun findAllByPhoneNumber(phoneNumber: String): ArrayList<AccountsPhoneNumber>
}