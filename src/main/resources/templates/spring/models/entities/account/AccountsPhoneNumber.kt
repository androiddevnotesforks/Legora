package #{PackageName}.models.entities.account

import javax.persistence.*

@Entity
@Table(name = AccountsPhoneNumber.TABLE_NAME)
data class AccountsPhoneNumber(
    @Id @GeneratedValue(strategy = GenerationType.TABLE) var id: Long = 0,
    @Column(name = PHONE_NUMBER, unique = false, updatable = false, nullable = false) val phoneNumber: String? = "",
    @Column(name = CODE, unique = false, updatable = false, nullable = false) val code: String? = "",
    @Column(name = USER_ID, unique = false, updatable = false, nullable = false) val userId: Long = 0
) {

    companion object {
        const val TABLE_NAME = "accounts_numbers"
        const val CODE = "code"
        const val USER_ID = "user_id"
        const val PHONE_NUMBER = "phone_number"
    }

}
