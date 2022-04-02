package utils

import errors.ProjectFieldsNotFoundException
import models.ProjectInformationItem
import models.ProjectItem

object ApplicationInformationManager {

    fun getFieldsByProjectKey(key: String): ArrayList<ProjectInformationItem> {
        return when(key) {
            ProjectItem.SINGLE_APP_ANDROID -> getAndroidFields()
            ProjectItem.MULTI_APP_ANDROID -> getAndroidMultiFields()
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
            ProjectInformationItem(key = ProjectInformationItem.PACKAGE, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Package Name ...", title = "Project Package Name")
        )
    }

    private fun getAndroidMultiFields(): ArrayList<ProjectInformationItem> {
        return arrayListOf(
            ProjectInformationItem(key = ProjectInformationItem.NAME, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Title ...", title = "Project Title"),
            ProjectInformationItem(key = ProjectInformationItem.PACKAGE, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Package Name ...", title = "Project Package Name")
        )
    }

}
