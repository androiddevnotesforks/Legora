package utils

import errors.ProjectFieldsNotFoundException
import models.ProjectInformationItem
import models.ProjectItem

object ApplicationInformationManager {

    fun getFieldsByProjectKey(key: String): ArrayList<ProjectInformationItem> {
        return when(key) {
            ProjectItem.SINGLE_APP_ANDROID -> getAndroidFields()
            ProjectItem.MULTI_APP_ANDROID -> getAndroidMultiFields()
            ProjectItem.NEXT_JS_APP_TS -> getNextJsFields()
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
            ProjectInformationItem(key = ProjectInformationItem.NAME, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Name ...", title = "Project Name"),
            ProjectInformationItem(key = ProjectInformationItem.PACKAGE, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Package Name ...", title = "Project Package Name")
        )
    }

    private fun getNextJsFields(): ArrayList<ProjectInformationItem> {
        return arrayListOf(
            ProjectInformationItem(key = ProjectInformationItem.NAME, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Name ...", title = "Project Name"),
            ProjectInformationItem(key = ProjectInformationItem.WEBSITE_URL, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Website Url ...", title = "Website Url"),
            ProjectInformationItem(key = ProjectInformationItem.VERSION, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Version ...", title = "Website Version"),
        )
    }

    private fun getAndroidMultiFields(): ArrayList<ProjectInformationItem> {
        return arrayListOf(
            ProjectInformationItem(key = ProjectInformationItem.NAME, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Name ...", title = "Project Name"),
            ProjectInformationItem(key = ProjectInformationItem.PACKAGE, type = ProjectInformationItem.TYPE_TEXT, hint = "Please Enter Project Package Name ...", title = "Project Package Name")
        )
    }

}
