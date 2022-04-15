package com.apartments.api.response

import com.apartments.api.errors.BaseException
import com.apartments.api.response.custom.GeneralErrorResponse
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.sql.Timestamp
import java.time.Instant
import javax.servlet.http.HttpServletRequest

object ResponseGenerator {

    fun getErrorResponse(exception: BaseException, request: HttpServletRequest): ResponseEntity<BaseResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(
            exception.getCode(),
            false,
            exception.message ?: "",
            Timestamp.from(Instant.now()).time,
            request.servletPath,
            exception.getPayloadResponse()
        ))
    }

    fun getPaginationResponse(request: HttpServletRequest?, page: Page<*>?): ListResponse {
        return ListResponse(
            HttpStatus.OK.value(),
            "Data Found",
            true,
            page?.content,
            PageInfo(page?.content?.size ?: 0, page?.number ?: 0, page?.totalPages ?: 0)
        )
    }

    fun getErrorResponse(exception: Throwable, request: HttpServletRequest): ResponseEntity<BaseResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            false,
            exception.message ?: "",
            Timestamp.from(Instant.now()).time,
            request.servletPath,
            GeneralErrorResponse(
                exception.javaClass.name,
                exception.cause?.message ?: "",
                exception.stackTrace
            )
        ))
    }

    fun getErrorResponse(exception: org.springframework.security.core.AuthenticationException, request: HttpServletRequest): ResponseEntity<BaseResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            false,
            exception.message ?: "",
            Timestamp.from(Instant.now()).time,
            request.servletPath,
            GeneralErrorResponse(
                exception.javaClass.name,
                exception.cause?.message ?: "",
                exception.stackTrace
            )
        ))
    }


}
