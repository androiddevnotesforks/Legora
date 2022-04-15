package com.apartments.api.models.req

data class OtpRequestBody(
    val method: String? = "",
    val phoneNumber: String? = "",
    val email: String? = ""
) {
    companion object {
        const val SMS_METHOD = "sms"
        const val MAIL_METHOD = "mail"
    }
}