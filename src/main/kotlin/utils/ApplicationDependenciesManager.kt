package utils

import errors.ApplicationDependenciesNotFoundException
import models.ApplicationDependency
import models.ProjectItem

object ApplicationDependenciesManager {

    fun getProjectDependenciesByKey(key: String): ArrayList<ApplicationDependency> {
        return when (key) {
            ProjectItem.SINGLE_APP_ANDROID -> getAndroidDependencies()
            ProjectItem.MULTI_APP_ANDROID -> getAndroidDependencies()
            else -> throw ApplicationDependenciesNotFoundException()
        }
    }

    private fun getAndroidDependencies(): ArrayList<ApplicationDependency> {
        return arrayListOf(
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", true, false),
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", true, false),
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", false, true),
            ApplicationDependency("Dependency 1", "Version 1.0.0", true, false),
            ApplicationDependency("Dependency 1", "Version 1.0.0", true, false),
        )
    }

}