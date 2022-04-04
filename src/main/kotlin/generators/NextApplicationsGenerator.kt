package generators

import generators.base.ProjectGeneratorImplementation
import generators.base.TemplatingFilesGenerator
import models.ApplicationDependency
import models.FileExtention
import models.ProjectInformationItem

class NextApplicationsGenerator constructor(
    private val isTypeScriptLanguage: Boolean,
    private val generatedPath: String,
    private val onGeneratedFileListener: (String) -> Unit
): TemplatingFilesGenerator(), ProjectGeneratorImplementation {

    override fun generateProject(dependencies: ArrayList<ApplicationDependency>, fields: HashMap<String, String>) {
        generatePackageJsonFile(fields[ProjectInformationItem.NAME] ?: "", fields[ProjectInformationItem.VERSION] ?: "")
        generateConfigurationFiles()
        generateTailwindConfigurations()
        generateProjectDirectories()
        if (isTypeScriptLanguage) {
            generateTypeScriptConfiguration()
        }
    }

    private fun generatePackageJsonFile(name: String, version: String) {
        val content = if (isTypeScriptLanguage) {
            getFileContent("templates/package-json/next-typescript-package-json.txt")
        } else {
            ""
        }

        content.replace("#{${ProjectInformationItem.VERSION}}", version)
        content.replace("#{${ProjectInformationItem.NAME}}", name)
        generateFile("package", content, FileExtention.JSON, generatedPath, onGeneratedFileListener)
    }

    private fun generateTypeScriptConfiguration() {
        val content = getFileContent("templates/configurations/tsconfig.json")
        generateFile("tsconfig", content, FileExtention.JSON, generatedPath, onGeneratedFileListener)
    }

    private fun generateTailwindConfigurations() {
        generateFile("tailwind.config", getFileContent("templates/configurations/tailwind.config.js"), FileExtention.JAVASCRIPT, generatedPath, onGeneratedFileListener)
        generateFile("postcss.config", getFileContent("templates/configurations/postcss.config.js"), FileExtention.JAVASCRIPT, generatedPath, onGeneratedFileListener)
        if (isTypeScriptLanguage) {
            generateFile("next-env.d", getFileContent("templates/configurations/next-env.d.ts"), FileExtention.TYPESCRIPT, generatedPath, onGeneratedFileListener)
        }
    }

    private fun generateConfigurationFiles() {
        generateFile("next.config", getFileContent("templates/configurations/next.config.js"), FileExtention.JAVASCRIPT, generatedPath, onGeneratedFileListener)
        generateFile(".gitignore", getFileContent("templates/gitignore/next-typescript-gitignore"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
    }

    private fun generateProjectDirectories() {
        generateDirectory("$generatedPath/styles", onGeneratedFileListener)
        generateDirectory("$generatedPath/public", onGeneratedFileListener)
        generateDirectory("$generatedPath/pages", onGeneratedFileListener)
        generateDirectory("$generatedPath/content", onGeneratedFileListener)
        generateDirectory("$generatedPath/components", onGeneratedFileListener)
        generateDirectory("$generatedPath/assets", onGeneratedFileListener)

        generateDirectory("$generatedPath/pages/api", onGeneratedFileListener)
        generateDirectory("$generatedPath/components/common", onGeneratedFileListener)
        generateDirectory("$generatedPath/components/home", onGeneratedFileListener)

        generateDirectory("$generatedPath/assets/images", onGeneratedFileListener)
        generateDirectory("$generatedPath/assets/audio", onGeneratedFileListener)

        generateDirectory("$generatedPath/content/constants", onGeneratedFileListener)
        generateDirectory("$generatedPath/content/utils", onGeneratedFileListener)
        generateDirectory("$generatedPath/content/content", onGeneratedFileListener)
        generateDirectory("$generatedPath/content/content/models", onGeneratedFileListener)
    }

}
