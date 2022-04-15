package com.apartments.api.response.custom

data class AuthResponse(
    val token: String,
    val account: Any?
)