package #{PackageName}.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, code = HttpStatus.UNAUTHORIZED, reason = "UNAUTHORIZED USER")
class UnAuthException constructor(
    override val message: String? = null,
    errorPayload: Any?
) : BaseException(message, errorPayload) {
    override fun getCode(): Int {
        return HttpStatus.UNAUTHORIZED.value()
    }
}