package generators.android

import generators.android.common.*
import generators.android.common.gradle.AndroidGradleDirectoriesGenerator
import generators.android.common.gradle.AndroidGradleWrapperProperitiesGenerator
import generators.android.common.modules.AndroidApplicationModulesDirectoriesGenerator
import generators.android.common.modules.app.AndroidAppBuildGradleFileGenerator
import generators.android.common.modules.data.AndroidDataBuildGradleFileGenerator
import generators.android.common.modules.domain.AndroidDomainBuildGradleFileGenerator
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
//        GradleFileRunnerGenerator(this, generatedFilePath).execute()
        GradleFileRunnerBatGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleProperitiesGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidApplicationDetailsGenerator(fields[ProjectInformationItem.PACKAGE] ?: "", generatedFilePath, onGeneratedFileListener).execute()
        AndroidGitIgnoreFileGenerator(isSingleModuleApplication, generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleDirectoriesGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleWrapperProperitiesGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidApplicationModulesDirectoriesGenerator(generatedFilePath, isSingleModuleApplication, onGeneratedFileListener).execute()

        AndroidAppBuildGradleFileGenerator(generatedFilePath.dropLast(1) + "/app", onGeneratedFileListener).execute()
        if (!isSingleModuleApplication) {
            AndroidDomainBuildGradleFileGenerator(generatedFilePath.dropLast(1) + "/domain", onGeneratedFileListener).execute()
            AndroidDataBuildGradleFileGenerator(generatedFilePath.dropLast(1) + "/data", onGeneratedFileListener).execute()
        }

    }

}
