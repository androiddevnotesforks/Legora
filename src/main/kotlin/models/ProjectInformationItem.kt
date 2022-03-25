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
    }
}
