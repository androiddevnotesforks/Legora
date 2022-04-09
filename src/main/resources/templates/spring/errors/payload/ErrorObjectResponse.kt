package #{PackageName}.errors.payload

data class ErrorObjectResponse(
    val fieldName: String,
    val message: String
)