package #{PackageName}.models.entities.account

import #{PackageName}.models.BaseEntity
import javax.persistence.*

@Entity
@Table(name = AccountAddress.TABLE_NAME)
data class AccountAddress(
    @Id @GeneratedValue(strategy = GenerationType.TABLE) var id: Long = 0,
    @Column(name = LONGITUDE, unique = false, nullable = false) var longitude: Double? = 0.0,
    @Column(name = IS_ENABLED, unique = false, nullable = false) var isEnabled: Boolean? = true,
    @Column(name = LATITUDE, unique = false, nullable = false) var latitude: Double? = 0.0,
    @Column(name = ACCOUNT_ID, unique = false, nullable = false) var accountId: Long? = 0L,
): BaseEntity() {
    companion object {
        const val TABLE_NAME = "accounts_addresses"
        const val LATITUDE = "location_latitude"
        const val LONGITUDE = "location_longitude"
        const val IS_ENABLED = "is_enabled"
        const val ACCOUNT_ID = "account_id"
    }
}
