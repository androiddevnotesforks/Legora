package com.apartments.api.models.req

data class VerifyOtpBody(
    val phoneNumber: String? = null,
    val verificationCode: String? = null
)