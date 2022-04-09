package #{PackageName}.models.entities

import #{PackageName}.models.BaseEntity
import java.time.Instant
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "reviews")
data class Test(
    @Id @GeneratedValue(strategy = GenerationType.TABLE) var id: Long = 0,
    @Column(name = "entity_id") var entityId: Long? = 0L,
    @Column(name = "account_id") var accountId: Long? = 0L,
    @Column(name = "type") var type: Int? = 1,
    @Column(name = "rate") var rate: Float? = 0.0f,
    @Column(name = "comment", length = 300) var comment: String? = "",
    @Column(name = "created_at", length = 300) var createdAt: Long? = timestamp(),
): BaseEntity() {
    companion object {
        const val TRIP_TYPE = 1
        const val PLACE_TYPE = 2

        fun timestamp(): Long {
            val rnd = Random()
            val date = Date(Math.abs(System.currentTimeMillis() - rnd.nextLong()))
            return date.time
        }
    }
}
