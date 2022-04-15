package com.apartments.api.response.custom

data class GeneralErrorResponse(
    val name: String,
    val message: String,
    val stackTrace: Any?
)