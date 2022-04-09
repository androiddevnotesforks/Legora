package #{PackageName}.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND, reason = "Data Not Found")
class EntityNotFoundException constructor(
    override val message: String? = null,
    errorPayload: Any?
) : BaseException(message, errorPayload) {
    override fun getCode(): Int {
        return HttpStatus.NOT_FOUND.value()
    }
}
