package generators

import generators.apps.AndroidApplicationGenerator
import generators.apps.AndroidLibraryGenerator
import generators.apps.NextApplicationsGenerator
import generators.apps.SpringBootGenerator
import models.ApplicationDependency
import models.ProjectInformationItem
import models.ProjectItem
import utils.ApplicationInformationManager

class ApplicationGeneratorManager constructor(
    private val generatedFilePath: String,
    private val onGeneratedFileListener: (String) -> Unit,
    private val onProjectFinishedListener: () -> Unit
) {

    fun generateProject(
        projectKey: String,
        dependencies: ArrayList<ApplicationDependency>,
        fields: ArrayList<ProjectInformationItem>
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
        }
    }

}
