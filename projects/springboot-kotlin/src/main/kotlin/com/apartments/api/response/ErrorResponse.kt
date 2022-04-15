package com.apartments.api.response

data class ErrorResponse(
    val code: Int,
    val status: Boolean = false,
    val message: String,
    val timestamp: Long,
    val path: String,
    val reason: Any?
): BaseResponse()