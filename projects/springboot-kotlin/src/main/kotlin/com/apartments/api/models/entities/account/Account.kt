package com.apartments.api.models.entities.account


import com.apartments.api.models.BaseEntity
import org.hibernate.Hibernate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Timestamp
import java.time.Instant
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Entity
@Table(name = Account.TABLE_NAME)
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.TABLE) var id: Long = 0,
    @Column(name = USER_NAME, unique = true, nullable = false) var userName: String? = "",
    @Column(name = FULL_NAME, nullable = false) var fullName: String? = "",
    @Column(name = PASSWORD, length = 200, nullable = false) var userPassword: String? = "",
    @Column(name = CREATED_AT, nullable = false) val createdAt: Long = Timestamp.from(Instant.now()).time,
    @Column(name = UPDATED_AT, nullable = true) val updatedAt: Long? = -1,
    @Column(name = BIRTH_YEAR, nullable = false) val birthYear: Int? = -1,
    @Column(name = BIO, length = 300, nullable = false) val bio: String? = "",
    @Column(name = WEBSITE_URL, nullable = true, length = 100) val website: String? = "",
    @Column(name = LOCATION, nullable = true) val location: String? = "",
    @Column(name = IMAGE, length = 400, nullable = false, updatable = true) val image: String? = "",
    @Column(name = COVER, length = 400, nullable = false, updatable = true) val cover: String? = "",
    @Column(name = COLOR, nullable = false) val color: String? = "",
    @Column(name = FIRST_NAME, nullable = true, updatable = true) var firstName: String? = "",
    @Column(name = LAST_NAME, nullable = true, updatable = true) var lastName: String? = "",
    @Column(name = PHONE_NUMBER, nullable = true, updatable = false, unique = true) val phoneNumber: String? = "",
    @Column(name = EMAIL, nullable = true, updatable = false, unique = true) val email: String? = "",
    @Column(name = PHONE_VERIFIED, updatable = true, nullable = false) var isVerified: Boolean = false,
    @Column(name = IS_ENABLED, updatable = true, nullable = false) var isUserEnabled: Boolean = true,
    @Column(name = IS_PREMIUM, updatable = true, nullable = false) var isPremium: Boolean = false,
    @Column(name = IS_MESSAGES_ENABLED, updatable = true, nullable = false) var isMessagesEnabled: Boolean = true,
    @Column(name = IS_PRIVATE, updatable = true, nullable = false) var isPrivate: Boolean = false,
    @Column(name = JOB_NAME, updatable = true, nullable = true) var jobName: String? = "",
    @Column(name = WORK, updatable = true, nullable = true) var workName: String? = "",
    @Column(name = STUDY, updatable = true, nullable = true) var highSchool: String? = "",
    @Column(name = REGISTERED_PLATFORM, updatable = false, nullable = false) val registeredPlatform: String? = "",
    @Column(name = COUNTRY_CODE, updatable = true, nullable = false) val countryCode: String? = "",
    @Column(name = STATUS) var status: String? = "DE_ACTIVATED",
    @Column(name = TYPE) val type: String? = "USER"
): BaseEntity(), UserDetails {

    companion object {
        const val TABLE_NAME = "accounts"
        const val USER_NAME = "user_name"
        const val FULL_NAME = "name"
        const val PASSWORD = "password"
        const val CREATED_AT = "created_at"
        const val UPDATED_AT = "updated_at"
        const val BIRTH_YEAR = "birth_year"
        const val BIO = "bio"
        const val WEBSITE_URL = "website"
        const val LOCATION = "location"
        const val IMAGE = "image"
        const val COVER = "cover"
        const val COLOR = "color"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val PHONE_NUMBER = "phone_number"
        const val EMAIL = "email"
        const val PHONE_VERIFIED = "is_phone_verified"
        const val IS_ENABLED = "is_enabled"
        const val IS_PREMIUM = "is_premium"
        const val IS_MESSAGES_ENABLED = "is_messages_enabled"
        const val IS_PRIVATE = "is_private"
        const val JOB_NAME = "job_name"
        const val WORK = "work_company"
        const val REGISTERED_PLATFORM = "registered_platform"
        const val COUNTRY_CODE = "country_code"
        const val STUDY = "high_school"
        const val AGE = "age"
        const val STATUS = "status"
        const val TYPE = "type"
        const val ID = "id"

        fun getFields(): ArrayList<String> {
            return ArrayList<String>().apply {
                this.add(USER_NAME)
                this.add(FULL_NAME)
                this.add(CREATED_AT)
                this.add(UPDATED_AT)
                this.add(BIRTH_YEAR)
                this.add(BIO)
                this.add(WEBSITE_URL)
                this.add(LOCATION)
                this.add(IMAGE)
                this.add(COVER)
                this.add(COLOR)
                this.add(FIRST_NAME)
                this.add(LAST_NAME)
                this.add(PHONE_NUMBER)
                this.add(EMAIL)
                this.add(PHONE_VERIFIED)
                this.add(IS_ENABLED)
                this.add(IS_PREMIUM)
                this.add(IS_MESSAGES_ENABLED)
                this.add(IS_PRIVATE)
                this.add(JOB_NAME)
                this.add(WORK)
                this.add(REGISTERED_PLATFORM)
                this.add(COUNTRY_CODE)
                this.add(STUDY)
                this.add(STATUS)
                this.add(TYPE)
                this.add(ID)
            }
        }
    }

    fun getObjectByFields(fields: Array<String>?): HashMap<String, Any?> {
        val accountInfo = getAccountInformation()
        if (fields.isNullOrEmpty()) {
            return accountInfo
        }

        val filteredFields = HashMap<String, Any?>()
        for (i: String in fields) {
            val value = accountInfo[i]
            if (value != null) {
                filteredFields[i] = value
            }

            if (i.equals(BIRTH_YEAR)) {
                filteredFields[AGE] = Calendar.getInstance().get(Calendar.YEAR) - (birthYear ?: 0)
            }
        }

        return filteredFields
    }

    private fun getAccountInformation(): HashMap<String, Any?> {
        return HashMap<String, Any?>().apply {
            this[ID] = id
            this[USER_NAME] = userName
            this[FULL_NAME] = fullName
            this[CREATED_AT] = createdAt
            this[UPDATED_AT] = updatedAt
            this[BIRTH_YEAR] = birthYear
            this[BIO] = bio
            this[WEBSITE_URL] = website
            this[LOCATION] = location
            this[IMAGE] = image
            this[COVER] = cover
            this[COLOR] = color
            this[FIRST_NAME] = firstName
            this[LAST_NAME] = lastName
            this[PHONE_NUMBER] = phoneNumber
            this[EMAIL] = email
            this[IS_MESSAGES_ENABLED] = isMessagesEnabled
            this[IS_PREMIUM] = isPremium
            this[PHONE_VERIFIED] = isVerified
            this[IS_ENABLED] = isEnabled
            this[IS_PRIVATE] = isPrivate
            this[JOB_NAME] = jobName
            this[WORK] = workName
            this[STUDY] = highSchool
            this[COUNTRY_CODE] = countryCode
            this[REGISTERED_PLATFORM] = registeredPlatform
            this[TYPE] = type
            this[STATUS] = status
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(type)
    }

    override fun getPassword(): String = userPassword ?: ""
    override fun getUsername(): String = userName ?: ""
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = isUserEnabled

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Account

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , userName = $userName , fullName = $fullName , userPassword = $userPassword , createdAt = $createdAt , updatedAt = $updatedAt , birthYear = $birthYear , bio = $bio , website = $website , location = $location , image = $image , cover = $cover , color = $color , firstName = $firstName , lastName = $lastName , phoneNumber = $phoneNumber , email = $email , isVerified = $isVerified , isUserEnabled = $isUserEnabled , isPremium = $isPremium , isMessagesEnabled = $isMessagesEnabled , isPrivate = $isPrivate , jobName = $jobName , workName = $workName , highSchool = $highSchool , registeredPlatform = $registeredPlatform , countryCode = $countryCode , status = $status , type = $type )"
    }

}
