package generators

import generators.apps.*
import models.ApplicationDependency
import models.ProjectInformationItem
import models.ProjectItem
import utils.ApplicationInformationManager

object ApplicationGeneratorManager {

    fun generateProject(
        projectKey: String,
        dependencies: ArrayList<ApplicationDependency>,
        fields: ArrayList<ProjectInformationItem>,
        generatedFilePath: String,
        onGeneratedFileListener: (String) -> Unit,
        onProjectFinishedListener: () -> Unit
    ) {
        val projectFields = ApplicationInformationManager.getProjectFields(fields)
        when (projectKey) {
            ProjectItem.SINGLE_APP_ANDROID -> AndroidApplicationGenerator(generatedFilePath, true, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.MULTI_APP_ANDROID -> AndroidApplicationGenerator(generatedFilePath, false, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.ANDROID_LIBRARY_TEMPLATE -> AndroidLibraryGenerator(generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.NEXT_JS_APP_TS -> NextApplicationsGenerator(true, generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.NEXT_JS_APP_JS -> NextApplicationsGenerator(false, generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.SPRING_J -> SpringBootGenerator(false, generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.SPRING_K -> SpringBootGenerator(true, generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.REACT_JAVA_SCRIPT -> ReactGenerator(true, generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.REACT_TYPE_SCRIPT -> ReactGenerator(false, generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
        }
        onProjectFinishedListener()
    }

}
