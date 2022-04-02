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
        const val NAME = "name"
        const val PACKAGE = "package"
        const val POPULAR_DEPENDENCIES = "popularDependencies"
        const val VIEW_BINDING = "viewBinding"
        const val SUPPORT_FLAVORS = "supportFlavors"
        const val SUPPORT_DATABASE = "supportedDatabase"
    }
}
