package generators.android

import generators.android.common.*
import generators.android.common.gradle.AndroidGradleDirectoriesGenerator
import generators.android.common.gradle.AndroidGradleWrapperProperitiesGenerator
import generators.base.ProjectGeneratorImplementation
import models.ApplicationDependency
import models.ProjectInformationItem

class AndroidApplicationGenerator constructor(
    private val generatedFilePath: String,
    private val isSingleModuleApplication: Boolean = false,
    private val onGeneratedFileListener: (String) -> Unit
): ProjectGeneratorImplementation {

    override fun generateProject(
        dependencies: ArrayList<ApplicationDependency>,
        fields: HashMap<String, String>
    ) {
        AndroidSettingsGradleGenerator(fields[ProjectInformationItem.NAME] ?: "", isSingleModuleApplication, generatedFilePath, onGeneratedFileListener).execute()
        AndroidLibrariesGradleFileGenerator(generatedFilePath, onGeneratedFileListener).execute()
        GradleFileRunnerGenerator(onGeneratedFileListener, generatedFilePath).execute()
        GradleFileRunnerBatGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleProperitiesGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidApplicationDetailsGenerator(fields[ProjectInformationItem.PACKAGE] ?: "", generatedFilePath, onGeneratedFileListener).execute()
        AndroidGitIgnoreFileGenerator(isSingleModuleApplication, generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleDirectoriesGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleWrapperProperitiesGenerator(generatedFilePath, onGeneratedFileListener).execute()
    }

}
