package models

enum class FileExtention constructor(public val key: String) {
    KOTLIN(".kt"),
    JAVA(".java"),
    JAVASCRIPT(".js"),
    TYPESCRIPT(".ts"),
    TYPESCRIPT_TSX(".tsx"),
    JAVASCRIPT_JSX(".jsx"),
    GRADLE(".gradle"),
    BAT(".bat"),
    EMPTY(""),
    PROPERTIES(".properties"),
    PRO(".pro"),
    JSON(".json"),
    XML(".xml"),
    CSS(".css"),
    YML(".yml"),
    DEV(".dev"),
    SCSS(".scss"),
    HTML(".html"),
    TXT(".txt")
}
