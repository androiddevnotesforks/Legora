package models

data class ProjectItem(
    val icon: String,
    val title: String,
    val description: String,
    val info: String,
    val key: String
) {
    companion object {
        const val SINGLE_APP_ANDROID = "saa"
        const val MULTI_APP_ANDROID = "maa"
        const val COMPOSE_APP_ANDROID = "caa"
        const val ANDROID_LIBRARY_TEMPLATE = "alt"
        const val REACT_JAVA_SCRIPT = "rjs"
        const val REACT_TYPE_SCRIPT = "rts"
        const val NEXT_JS_APP_JS = "nextts"
        const val NEXT_JS_APP_TS = "nextjs"
        const val DESKTOP_COMPOSE = "desktopCompose"
        const val GRADLE = "gradle"
        const val KTOR = "ktor"
        const val NEST_JS = "nestjs"
        const val SPRING_J = "spj"
        const val SPRING_K = "spk"
    }
}
