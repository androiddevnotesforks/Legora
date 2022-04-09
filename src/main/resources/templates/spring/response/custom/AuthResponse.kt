package #{PackageName}.response.custom

data class AuthResponse(
    val token: String,
    val account: Any?
)