package utils

import errors.ProjectFieldsNotFoundException
import models.ProjectInformationItem
import models.ProjectItem

object ApplicationInformationManager {

    fun getFieldsByProjectKey(key: String): ArrayList<ProjectInformationItem> {
        return when(key) {
            ProjectItem.SINGLE_APP_ANDROID -> getAndroidFields()
            else -> throw ProjectFieldsNotFoundException()
        }
    }

    fun getProjectFields(fields: ArrayList<ProjectInformationItem>): HashMap<String, String> {
        val result = HashMap<String, String>()
        fields.forEach {
            result[it.key] = it.selectedValue
        }
        return result
    }

    private fun getAndroidFields(): ArrayList<ProjectInformationItem> {
        return arrayListOf(
            ProjectInformationItem(key = ProjectInformationItem.NAME, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Title ...", title = "Project Title"),
            ProjectInformationItem(key = ProjectInformationItem.PACKAGE, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Package Name ...", title = "Project Package Name"),
            ProjectInformationItem(key = ProjectInformationItem.POPULAR_DEPENDENCIES, type = ProjectInformationItem.TYPE_SWITCH, title = "Popular Dependencies"),
            ProjectInformationItem(key = ProjectInformationItem.SUPPORT_DATABASE, type = ProjectInformationItem.TYPE_SWITCH, title = "Is Application Support Database"),
            ProjectInformationItem(key = ProjectInformationItem.SUPPORT_FLAVORS, type = ProjectInformationItem.TYPE_SWITCH, title = "Is Application Support Flavors"),
            ProjectInformationItem(key = ProjectInformationItem.VIEW_BINDING, type = ProjectInformationItem.TYPE_SWITCH, title = "Is Application Support View Binding"),
        )
    }

}
