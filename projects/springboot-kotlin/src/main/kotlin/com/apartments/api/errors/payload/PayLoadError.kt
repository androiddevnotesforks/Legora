package com.apartments.api.errors.payload

open class PayLoadError(
    val message: String? = "",
    val payload: Any? = null
)