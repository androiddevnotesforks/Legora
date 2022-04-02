package generators.android.common

import generators.base.FileGenerator
import models.FileExtention

class AndroidGitIgnoreFileGenerator constructor(
    private val isSingleModuleApplication: Boolean = false,
    private val generatedFilePath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String {
        return ".gitignore"
    }

    override fun getFileContent(): String {
        return if (isSingleModuleApplication) {
            "*.iml\n" +
                    ".gradle\n" +
                    "/local.properties\n" +
                    "/.idea/caches\n" +
                    "/.idea/libraries\n" +
                    "/.idea/modules.xml\n" +
                    "/.idea/workspace.xml\n" +
                    "/.idea/navEditor.xml\n" +
                    "/.idea/assetWizardSettings.xml\n" +
                    ".DS_Store\n" +
                    "/build\n" +
                    "/captures\n" +
                    ".externalNativeBuild\n" +
                    ".cxx\n" +
                    "local.properties\n"
        } else {
            "*.iml\n" +
                    ".gradle\n" +
                    "/local.properties\n" +
                    "/.idea/caches\n" +
                    "/.idea/libraries\n" +
                    "/.idea/modules.xml\n" +
                    "/.idea/workspace.xml\n" +
                    "/.idea/navEditor.xml\n" +
                    "/.idea/assetWizardSettings.xml\n" +
                    ".DS_Store\n" +
                    "/build\n" +
                    "/captures\n" +
                    ".externalNativeBuild\n" +
                    ".cxx\n" +
                    "local.properties\n" +
                    "\n" +
                    "/data/build\n" +
                    "/domain/build\n"
        }
    }

    override fun getFileExt(): FileExtention = FileExtention.EMPTY
    override fun getFilePath(): String = generatedFilePath

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }

}
