package models

class ProjectInformationItem(
    val key: String = "",
    val type: Int,
    val hint: String = "",
    val title: String = "",
    var selectedValue: String = ""
) {
    companion object {
        const val TYPE_TEXT = 1
        const val TYPE_SWITCH = 2

        // Project Keys
        const val NAME = "Name"
        const val PACKAGE = "package"
        const val WEBSITE_URL = "url"
        const val VERSION = "Version"
        const val ARTIFACE = "Artifact"
        const val GROUP = "group"
        const val DESCRIPTION = "description"
        const val GIT_LINK = "gitlink"
        const val REPO_NAME = "repoName"
    }
}
