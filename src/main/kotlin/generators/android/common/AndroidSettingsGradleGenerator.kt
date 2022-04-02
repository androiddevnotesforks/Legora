package generators.android.common

import generators.base.FileGenerator
import models.FileExtention

class AndroidSettingsGradleGenerator constructor(
    private val name: String,
    private val isSingleModuleApplication: Boolean = false,
    private val generatedFilePath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String {
        return "settings"
    }

    override fun getFileContent(): String {
        return if (isSingleModuleApplication) {
            "dependencyResolutionManagement {\n" +
                    "    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)\n" +
                    "    repositories {\n" +
                    "        google()\n" +
                    "        mavenCentral()\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "rootProject.name = \"${name}\"\n" +
                    "include ':app'"
        } else {
            "dependencyResolutionManagement {\n" +
                    "    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)\n" +
                    "    repositories {\n" +
                    "        google()\n" +
                    "        mavenCentral()\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "rootProject.name = \"Legora Application\"\n" +
                    "include ':app'\n" +
                    "include ':data'\n" +
                    "include ':domain'"
        }
    }

    override fun getFileExt(): FileExtention = FileExtention.GRADLE
    override fun getFilePath(): String = generatedFilePath

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }

}
