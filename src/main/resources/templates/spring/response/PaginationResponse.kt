package #{PackageName}.response

data class PageInfo(
    val size: Int,
    val currentPage: Int,
    val totalPages: Int
)