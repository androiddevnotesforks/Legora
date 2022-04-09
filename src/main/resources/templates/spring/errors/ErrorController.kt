package #{PackageName}.errors

import #{PackageName}.response.BaseResponse
import #{PackageName}.response.ResponseGenerator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.IOException
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorController : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [Throwable::class, UnAuthException::class, HttpClientErrorException.Unauthorized::class, IOException::class, Exception::class, HttpClientErrorException.Forbidden::class, WebClientResponseException.Forbidden::class])
    fun handleInvalidDataException(httpServletRequest: HttpServletRequest, exception: Throwable): ResponseEntity<BaseResponse> {
        return if (exception is BaseException) {
            ResponseGenerator.getErrorResponse(exception, httpServletRequest)
        } else {
            ResponseGenerator.getErrorResponse(exception, httpServletRequest)
        }
    }

}