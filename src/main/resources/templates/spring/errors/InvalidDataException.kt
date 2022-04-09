package #{PackageName}.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "Invalid Input")
class InvalidDataException constructor(
    override val message: String? = null,
    errorPayload: Any?
) : BaseException(message, errorPayload) {
    override fun getCode(): Int {
        return HttpStatus.BAD_REQUEST.value()
    }
}