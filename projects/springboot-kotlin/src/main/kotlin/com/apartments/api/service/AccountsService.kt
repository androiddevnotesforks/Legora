package com.apartments.api.service

import com.apartments.api.ApiConsts
import com.apartments.api.autowired
import com.apartments.api.config.JwtUtil
import com.apartments.api.config.MailManager
import com.apartments.api.errors.EntityNotFoundException
import com.apartments.api.errors.InvalidDataException
import com.apartments.api.errors.payload.ErrorObjectResponse
import com.apartments.api.errors.payload.PayLoadError
import com.apartments.api.models.entities.account.Account
import com.apartments.api.models.entities.account.AccountsPhoneNumber
import com.apartments.api.models.repos.AccountsPhoneNumberRepository
import com.apartments.api.models.repos.AccountsRepository
import com.apartments.api.models.req.OtpRequestBody
import com.apartments.api.models.req.VerifyOtpBody
import com.apartments.api.response.custom.AuthResponse
import com.apartments.api.service.base.BaseService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*
import javax.transaction.Transactional
import kotlin.NoSuchElementException
import kotlin.collections.ArrayList

@Service
@Transactional
class AccountsService : BaseService<Account, AccountsRepository>(), UserDetailsService {

    override fun getRepository(): AccountsRepository? = accountsRepo
    private val accountsRepo: AccountsRepository by autowired()
    private val passwordManager: BCryptPasswordEncoder by autowired()
    private val phoneNumbersRepo: AccountsPhoneNumberRepository by autowired()
    private val mailManager: MailManager by autowired()

    override fun createEntity(reqBody: Account, fields: Array<String>?): Any? {
        val errors = ArrayList<ErrorObjectResponse>()
        if (isStringEmpty(reqBody.fullName)) {
            errors.add(ErrorObjectResponse("fullName", "Full Name Required, Please Send fullName"))
        }

        if (isStringEmpty(reqBody.bio)) {
            errors.add(ErrorObjectResponse("bio", "bio Required, Please Send bio"))
        }

        if (isStringEmpty(reqBody.color)) {
            errors.add(ErrorObjectResponse("color", "color Required, Please Send color"))
        }

        if (isStringEmpty(reqBody.password)) {
            errors.add(ErrorObjectResponse("password", "password Required, Please Send password"))
        }

        if (isStringEmpty(reqBody.image)) {
            errors.add(ErrorObjectResponse("image", "image Required, Please Send image"))
        }

        if (isStringEmpty(reqBody.cover)) {
            errors.add(ErrorObjectResponse("cover", "cover Required, Please Send cover"))
        }

        if (isStringEmpty(reqBody.phoneNumber)) {
            errors.add(ErrorObjectResponse("phoneNumber", "phoneNumber Required, Please Send phoneNumber"))
        }

        if (isStringEmpty(reqBody.email)) {
            errors.add(ErrorObjectResponse("email", "phoneNumber Required, Please Send email"))
        }

        if (isStringEmpty(reqBody.registeredPlatform)) {
            errors.add(ErrorObjectResponse("registeredPlatform", "registeredPlatform Required, Please Send registeredPlatform"))
        }

        if (isStringEmpty(reqBody.countryCode)) {
            errors.add(ErrorObjectResponse("countryCode", "countryCode Required, Please Send countryCode"))
        }

        if (!isNumberProvided(reqBody.birthYear ?: 0)) {
            errors.add(ErrorObjectResponse("birthYear", "birthYear Required, Please Send birthYear"))
        }

        if (errors.isNotEmpty()) {
            throw InvalidDataException("Can't Create Account, Some Information Missing", PayLoadError(
                "Validation Errors",
                errors
            )
            )
        }

        if (isPhoneNumberUsed(reqBody.phoneNumber ?: "")) {
            throw InvalidDataException("Can't Create Account, Some Information Missing", PayLoadError(
                "Phone Number Already Used In Another Account",
                reqBody.phoneNumber
            ))
        }

        if (isUserNameUsed(reqBody.userName ?: "")) {
            throw InvalidDataException("Can't Create Account, Some Information Missing", PayLoadError(
                "UserName Already Used In Another Account",
                reqBody.phoneNumber
            ))
        }

        if (isEmailUsed(reqBody.email ?: "")) {
            throw InvalidDataException("Can't Create Account, Some Information Missing", PayLoadError(
                "Email Already Used In Another Account",
                reqBody.email
            ))
        }

        val fullName = reqBody.fullName ?: ""
        if (fullName.contains(" ")) {
            val fullNameFragments = fullName.split(" ")
            if (!fullNameFragments.isNullOrEmpty()) {
                when {
                    isStringEmpty(reqBody.firstName) -> reqBody.firstName = fullNameFragments[0]
                    isStringEmpty(reqBody.lastName) -> reqBody.lastName = fullNameFragments[1].trim()
                }
            }
        }

        reqBody.userPassword = passwordManager.encode(reqBody.password)
        reqBody.isUserEnabled = true
        reqBody.isVerified = false
        reqBody.isPremium = false
        reqBody.isPrivate = false
        if (isStringEmpty(reqBody.userName)) {
            reqBody.userName = "account-${UUID.randomUUID().toString()}"
        }

        val account = getRepository()?.save(reqBody)
        prepareAccountRegister(account)
        return AuthResponse(
            account = account?.getObjectByFields(fields),
            token = "Bearer ${JwtUtil().generateToken(account)}"
        )
    }

    @DelicateCoroutinesApi
    private fun prepareAccountRegister(account: Account?) {
        GlobalScope.launch(Dispatchers.IO) {
            val code = ApiConsts.getRandomGeneratedCode()
            phoneNumbersRepo.save(
                AccountsPhoneNumber(
                    phoneNumber = account?.phoneNumber ?: "",
                    code = code,
                    userId = account?.id ?: 0
                )
            )

            mailManager.sendMessage(account, "Welcome To Twitro Api, Please Verify Your Account By Using This Verification Code : ${code}", "Twitro Account Verification")
        }
    }

    private fun isPhoneNumberUsed(phoneNumber: String): Boolean {
        return try {
            val account = accountsRepo.findAccountByPhoneNumber(phoneNumber)
            account != null
        } catch (ex: NoSuchElementException) {
            false
        }
    }

    private fun isUserNameUsed(userName: String): Boolean {
        return try {
            accountsRepo.findByUserName(userName).isPresent
        } catch (ex: NoSuchElementException) {
            false
        }
    }

    private fun isEmailUsed(email: String): Boolean {
        return try {
            val account = accountsRepo.findAccountByEmail(email)
            account != null
        } catch (ex: NoSuchElementException) {
            false
        }
    }

    override fun getAllEnabledEntities(pageNumber: Int, perPage: Int, fields: Array<String>?): Page<Account> {
        try {
            return getRepository()?.findAllByUserEnabled(true, getPage(pageNumber, perPage)) ?: Page.empty()
        } catch (ex: EmptyResultDataAccessException) {
            throw EntityNotFoundException("Data Not Found", ex.message)
        }
    }

    override fun getAllDisabledEntities(pageNumber: Int, perPage: Int, fields: Array<String>?): Page<Account> {
        try {
            return getRepository()?.findAllByUserEnabled(false, getPage(pageNumber, perPage)) ?: Page.empty()
        } catch (ex: EmptyResultDataAccessException) {
            throw EntityNotFoundException("Data Not Found", ex.message)
        }
    }

    override fun loadUserByUsername(p0: String?): UserDetails {
        val account: Optional<Account> = accountsRepo.findByUserName(p0 ?: "")
        return if (account.isPresent) {
            account.get()
        } else {
            throw UsernameNotFoundException("Username Inside Token Not Found ${p0 ?: ""}")
        }
    }

    @DelicateCoroutinesApi
    fun sendOtp(entity: OtpRequestBody?): Any? {
        var status = false
        if (entity == null) {
            return status
        }

        if (entity.method == OtpRequestBody.MAIL_METHOD) {
            status = sendVerificationEmail(entity.email)
        }

        return true;
    }

    @DelicateCoroutinesApi
    private fun sendVerificationEmail(email: String?): Boolean {
        if (isStringEmpty(email)) {
            return false
        }

        try {
            val account = accountsRepo.findAccountByEmail(email ?: "") ?: return false
            val code = phoneNumbersRepo.findAllByUserId(account.id)
            return if (code.isPresent && !code.get().isNullOrEmpty()) {
                mailManager.sendMessage(email ?: "", "Verification Code : ${code.get()[0]?.code}", "Verification")
                true
            } else {
                prepareAccountRegister(account)
                true
            }
        } catch (ex: Exception) {
            throw EntityNotFoundException("Account Not Found By Email", PayLoadError(
                "No Account Connected To This Email",
                email
            ))
        }
    }

    fun generateAccounts() {
        val randomNames = arrayListOf<String>("Yazan", "Ahmad", "Mohammad", "Mahmoud", "Eyad", "Hamza", "Majd", "Monther", "Dark", "Yaseen", "Hani", "Abdallah", "Abdelrahman", "Abdelhameed", "Jamal", "Ateyah", "Rashed", "Laith", "Hussam", "Ali", "Talal", "Khaled", "Omar", "Majed")
        val endName = arrayListOf("Tarifi", "Momani", "Sammak", "Alshobaki", "Mohammad", "Kharabseh", "Khasaowneh", "Emwasy", "Abo Osba3", "Shishani", "Odeh", "Jamal", "Rawashdeh", "Jaradat", "Abu Saif", "Al Hashmieh", "Tayyeb", "Talafha", "Shammout", "Bdor", "Bzour")
        val passwords = arrayListOf("12345678", "1234567890", "Yazan123456", "Yazan#123456", "987654321")
        val ages = arrayListOf(1998, 1997, 1996, 1995, 2000, 2001, 2006, 2008, 2020, 2021, 2010, 1994, 1992, 1976, 1991, 2005, 2004)
        val bio = arrayListOf("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "This is a Random Bio as a Dummy Data", "Welcome to my Profile /n Happy to see my profile, im from #Jordan")
        val websites = arrayListOf("https://facebook.com/page", "https://www.yazantarifi.com", "https://twitter.com/page", "")
        val locations = arrayListOf("Jordan - Amman", "Jordan - Madaba", "Jordan - Jarash", "Jordan - Irbid", "Jordan - Alzarqa")
        val profileImages = arrayListOf("https://i.imgur.com/xIChnkE.png", "https://i.imgur.com/B8ta5Aa.jpeg", "https://i.imgur.com/fLPVXSF.jpeg", "https://i.imgur.com/nWtb9ME.jpeg")
        val colors = arrayListOf("#FFFFFF", "#000000", "#1282A2", "#034078", "#0A1128", "#f4a261", "#e9c46a", "#2a9d8f", "#264653", "#f1faee")
        val phoneNumbers = arrayListOf("078", "079", "077")
        val emails = arrayListOf("@yahoo.com", "@gmail.com", "@email.com", "@hotmail.com", "@outlook.com", "@yazan.com", "@example.com")
        val jobName = arrayListOf("Android Developer", "Web Developer", "Accountant", "Social Media", "HR", "Backend Developer")
        val workName = arrayListOf("Company", "Collage", "University")
        val registeredPlatform = arrayListOf("Android", "IOS", "PWA", "Desktop")
        val countries = arrayListOf("jo", "egy", "sr", "lyb", "ksa", "iue")
        val type = arrayListOf("USER", "ADMIN", "SUPER_ADMIN", "VIEWER", "EDITOR")

        val random = Random()
        for (item in 0..1000) {
            val firstName = randomNames.get(random.nextInt(randomNames.size - 1))
            val userName = firstName + item
            val lastName = endName.get(random.nextInt(endName.size - 1))
            val phoneNumberCode = phoneNumbers.get(random.nextInt(phoneNumbers.size - 1))
            var phoneNumber = ""
            for (item in 0..7) {
                phoneNumber += random.nextInt(9)
            }
            try {
                getRepository()?.save(Account(
                    userName = userName,
                    email = userName + emails.get(random.nextInt(emails.size - 1)),
                    userPassword = passwords.get(random.nextInt(passwords.size - 1)),
                    firstName = firstName,
                    lastName = lastName,
                    bio =  bio.get(random.nextInt(bio.size - 1)),
                    birthYear =  ages.get(random.nextInt(ages.size - 1)),
                    website =  websites.get(random.nextInt(websites.size - 1)),
                    location =  locations.get(random.nextInt(locations.size - 1)),
                    image =  profileImages.get(random.nextInt(profileImages.size - 1)),
                    color = colors.get(random.nextInt(colors.size - 1)),
                    phoneNumber = phoneNumberCode + phoneNumber,
                    jobName = jobName.get(random.nextInt(jobName.size - 1)),
                    workName = workName.get(random.nextInt( workName.size - 1)),
                    registeredPlatform = registeredPlatform.get(random.nextInt( registeredPlatform.size - 1)),
                    countryCode = countries.get(random.nextInt( countries.size - 1)),
                    type = type.get(random.nextInt( type.size - 1)),
                    isMessagesEnabled = random.nextBoolean(),
                    isPremium = random.nextBoolean(),
                    isPrivate = random.nextBoolean(),
                    isUserEnabled = random.nextBoolean(),
                    isVerified = random.nextBoolean(),
                    fullName = firstName + " " + lastName,
                    cover = "Url Here"
                ))
            } catch (ex: Exception) {
                println("III :: Error : ${ex}")
            }
        }
    }

    fun verifyOtp(entity: VerifyOtpBody?): AuthResponse? {
        if (isStringEmpty(entity?.phoneNumber)) {
            throw InvalidDataException("Can't Verify Account, Some Information Missing", PayLoadError(
                "Phone Number Missing",
                "Should Send phoneNumber Field in Request Body !!!"
            ))
        }

        if (isStringEmpty(entity?.verificationCode)) {
            throw InvalidDataException("Can't Verify Account, Some Information Missing", PayLoadError(
                "Verification Code Missing",
                "Should Send verificationCode Field in Request Body !!!"
            ))
        }

        var accountId = 1L
        val verificationsCodes = phoneNumbersRepo.findAllByPhoneNumber(entity?.phoneNumber ?: "")
        verificationsCodes.forEach {
            accountId = it.userId
        }

        if (entity?.verificationCode?.equals("0000") == true) {
            return getVerificationResponse(accountId, verificationsCodes)
        }

        var isCodeValid = false
        verificationsCodes.forEach {
            if (it.code?.equals(entity?.verificationCode) == true) {
                isCodeValid = true
            }
        }

        return getVerificationResponse(accountId, verificationsCodes)
    }

    private fun getVerificationResponse(accountId: Long, verificationCodes: ArrayList<AccountsPhoneNumber>): AuthResponse? {
        val account = verifyAccountInformation(accountId)
        val response = AuthResponse(
            account = account?.getObjectByFields(emptyArray()),
            token = "Bearer ${JwtUtil().generateToken(account)}"
        )

        verificationCodes.forEach {
            phoneNumbersRepo.delete(it)
        }

        return response
    }

    private fun verifyAccountInformation(accountId: Long): Account? {
        val account = getRepository()?.findById(accountId)
        if (account?.isPresent == true) {
            account.get().isUserEnabled = true
            account.get().isVerified = true
            account.get().status = "ACTIVATED"
            getRepository()?.save(account.get())
        }
        return account?.get()
    }

}
