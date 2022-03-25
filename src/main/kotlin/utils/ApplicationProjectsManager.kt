package utils

import models.ProjectItem

object ApplicationProjectsManager {

    fun getProjectsList(): ArrayList<ProjectItem> {
        return arrayListOf(
            ProjectItem(ApplicationIcons.ANDROID_LOGO, ApplicationStrings.ANDROID_TITLE, ApplicationStrings.ANDROID_DESCRIPTION_SINGLE, "", ProjectItem.SINGLE_APP_ANDROID),
            ProjectItem(ApplicationIcons.ANDROID_LOGO, ApplicationStrings.ANDROID_TITLE, ApplicationStrings.ANDROID_DESCRIPTION_MULTI, "", ProjectItem.MULTI_APP_ANDROID),
            ProjectItem(ApplicationIcons.ANDROID_LOGO, ApplicationStrings.ANDROID_TITLE, ApplicationStrings.ANDROID_DESCRIPTION_JETPACK, "", ProjectItem.COMPOSE_APP_ANDROID),
            ProjectItem(ApplicationIcons.REACT_ICON, ApplicationStrings.REACT_TITLE, ApplicationStrings.REACT_DESCRIPTION_JS, "", ProjectItem.REACT_JAVA_SCRIPT),
            ProjectItem(ApplicationIcons.REACT_ICON, ApplicationStrings.REACT_TITLE, ApplicationStrings.REACT_DESCRIPTION_TS, "", ProjectItem.REACT_TYPE_SCRIPT),
            ProjectItem(ApplicationIcons.NEXT_ICON, ApplicationStrings.NEXT_TITLE, ApplicationStrings.NEXT_JS_DESCRIPTION, "", ProjectItem.NEXT_JS_APP_JS),
            ProjectItem(ApplicationIcons.NEXT_ICON, ApplicationStrings.NEXT_TITLE, ApplicationStrings.NEXT_TS_DESCRIPTION, "", ProjectItem.NEXT_JS_APP_TS),
            ProjectItem(ApplicationIcons.COMPOSE, ApplicationStrings.COMPOSE_TITLE, ApplicationStrings.COMPOSE_DESCRIPTION, "", ProjectItem.DESKTOP_COMPOSE),
            ProjectItem(ApplicationIcons.GRADLE, ApplicationStrings.GRADLE_TITLE, ApplicationStrings.GRADLE_DESCRIPTION, "", ProjectItem.GRADLE)
        )
    }

}
