package com.apartments.api.response

import org.springframework.http.HttpStatus

data class SuccessResponse(
    var code: Int = HttpStatus.OK.value(),
    var message: String = "Data Found",
    var status: Boolean = true,
    var data: Any? = null
): BaseResponse()