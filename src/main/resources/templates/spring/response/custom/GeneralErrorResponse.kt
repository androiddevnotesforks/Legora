package #{PackageName}.response.custom

data class GeneralErrorResponse(
    val name: String,
    val message: String,
    val stackTrace: Any?
)