package generators.android

import generators.android.common.*
import generators.android.common.gradle.AndroidGradleDirectoriesGenerator
import generators.android.common.gradle.AndroidGradleWrapperProperitiesGenerator
import generators.android.modules.*
import generators.android.modules.build.AndroidBuildGradleSingleGenerator
import generators.android.modules.app.AndroidAppBuildGradleFileGenerator
import generators.android.modules.app.AndroidAppManifestGenerator
import generators.android.modules.app.AndroidAppResourcesFilesGenerator
import generators.android.modules.build.AndroidBuildGradleMultiGenerator
import generators.android.modules.data.AndroidDataBuildGradleFileGenerator
import generators.android.modules.data.AndroidDataManifestGenerator
import generators.android.modules.domain.AndroidDomainBuildGradleFileGenerator
import generators.android.modules.domain.AndroidDomainManifestGenerator
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
        AndroidGradleRunnerGenerator(generatedFilePath, onGeneratedFileListener).execute()
        GradleFileRunnerBatGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleProperitiesGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidApplicationDetailsGenerator(fields[ProjectInformationItem.PACKAGE] ?: "", generatedFilePath, onGeneratedFileListener).execute()
        AndroidGitIgnoreFileGenerator(isSingleModuleApplication, generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleDirectoriesGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidGradleWrapperProperitiesGenerator(generatedFilePath, onGeneratedFileListener).execute()
        AndroidApplicationModulesDirectoriesGenerator(generatedFilePath, isSingleModuleApplication, onGeneratedFileListener).execute()

        AndroidAppBuildGradleFileGenerator("$generatedFilePath/app", onGeneratedFileListener).execute()
        ProguardRulesGenerator("$generatedFilePath/app", onGeneratedFileListener).execute()
        GoogleServicesFileGenerator("$generatedFilePath/app", onGeneratedFileListener).execute()
        AndroidAppManifestGenerator(fields[ProjectInformationItem.PACKAGE] ?: "", fields[ProjectInformationItem.NAME] ?: "", generatedFilePath, onGeneratedFileListener).execute()
        if (!isSingleModuleApplication) {
            AndroidDomainBuildGradleFileGenerator("$generatedFilePath/domain", onGeneratedFileListener).execute()
            AndroidDataBuildGradleFileGenerator("$generatedFilePath/data", onGeneratedFileListener).execute()

            ProguardRulesGenerator("$generatedFilePath/domain", onGeneratedFileListener).execute()
            ProguardRulesGenerator("$generatedFilePath/data", onGeneratedFileListener).execute()

            AndroidDomainManifestGenerator(fields[ProjectInformationItem.PACKAGE] ?: "", generatedFilePath, onGeneratedFileListener).execute()
            AndroidDataManifestGenerator(fields[ProjectInformationItem.PACKAGE] ?: "", generatedFilePath, onGeneratedFileListener).execute()
        }

        if (isSingleModuleApplication) {
            AndroidBuildGradleSingleGenerator(generatedFilePath, onGeneratedFileListener).execute()
        } else {
            AndroidBuildGradleMultiGenerator(generatedFilePath, onGeneratedFileListener).execute()
        }

        AndroidPackageNameGenerator(generatedFilePath, fields[ProjectInformationItem.PACKAGE] ?: "", isSingleModuleApplication, onGeneratedFileListener).execute()
        AndroidResourcesDirectoriesGenerator(generatedFilePath, isSingleModuleApplication, onGeneratedFileListener).execute()
        AndroidAppResourcesFilesGenerator(generatedFilePath, onGeneratedFileListener).execute()
    }

}
