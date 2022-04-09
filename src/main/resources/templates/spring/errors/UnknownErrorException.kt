package #{PackageName}.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unknown Error")
class UnknownErrorException constructor(
    override val message: String? = null,
    errorPayload: Any?
) : BaseException(message, errorPayload) {
    override fun getCode(): Int {
        return HttpStatus.INTERNAL_SERVER_ERROR.value()
    }
}