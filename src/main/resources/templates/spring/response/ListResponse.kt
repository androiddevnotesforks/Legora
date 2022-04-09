package #{PackageName}.response

import org.springframework.http.HttpStatus

data class ListResponse(
    var code: Int = HttpStatus.OK.value(),
    var message: String = "Data Found",
    var status: Boolean = true,
    var data: Any? = null,
    val pagination: PageInfo
): BaseResponse()