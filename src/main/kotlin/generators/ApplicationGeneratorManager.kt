package generators

import generators.android.AndroidApplicationGenerator
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
            ProjectItem.NEXT_JS_APP_TS -> NextApplicationsGenerator(true, generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
            ProjectItem.NEXT_JS_APP_JS -> NextApplicationsGenerator(false, generatedFilePath, onGeneratedFileListener).generateProject(dependencies, projectFields)
        }
    }

}
