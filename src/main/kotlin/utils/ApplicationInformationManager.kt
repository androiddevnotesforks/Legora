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

    private fun getAndroidFields(): ArrayList<ProjectInformationItem> {
        return arrayListOf(
            ProjectInformationItem(key = "name", type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Title ...", title = "Project Title"),
            ProjectInformationItem(key = "package", type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Package Name ...", title = "Project Package Name"),
            ProjectInformationItem(key = "popularDependencies", type = ProjectInformationItem.TYPE_SWITCH, title = "Popular Dependencies"),
            ProjectInformationItem(key = "supportedDatabase", type = ProjectInformationItem.TYPE_SWITCH, title = "Is Application Support Database"),
            ProjectInformationItem(key = "supportFlavors", type = ProjectInformationItem.TYPE_SWITCH, title = "Is Application Support Flavors"),
            ProjectInformationItem(key = "viewBinding", type = ProjectInformationItem.TYPE_SWITCH, title = "Is Application Support View Binding"),
        )
    }

}
