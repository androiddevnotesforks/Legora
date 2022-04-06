package generators.apps

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
        generateFiles(fields[ProjectInformationItem.NAME] ?: "")
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

        generateDirectory("$generatedPath/components/home", onGeneratedFileListener)
        generateDirectory("$generatedPath/components/common", onGeneratedFileListener)
    }

    private fun generateFiles(projectName: String) {
        generateFile("globals", getFileContent("templates/css/next-js-global-style.txt"), FileExtention.CSS, "$generatedPath/styles", onGeneratedFileListener)
        generateFile("manifest", getFileContent("templates/configurations/next-js-manifest.txt").replace("#{Name}", projectName), FileExtention.JSON, "$generatedPath/public", onGeneratedFileListener)
        generateFile("index", "", FileExtention.JAVASCRIPT, "$generatedPath/assets", onGeneratedFileListener)

        generateFile("LegoraLayout", getFileContent("templates/next/layout.txt"), FileExtention.TYPESCRIPT_TSX, "$generatedPath/components", onGeneratedFileListener)
        generateFile("InnerToolbarComponent", getFileContent("templates/next/inner-toolbar.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT_TSX, "$generatedPath/components/common", onGeneratedFileListener)
        generateFile("ToolbarComponent", getFileContent("templates/next/toolbar.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT_TSX, "$generatedPath/components/common", onGeneratedFileListener)

        generateFile("HomeCodeSnippetComponent", getFileContent("templates/next/home-code-snippet.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT_TSX, "$generatedPath/components/home", onGeneratedFileListener)
        generateFile("HomeInverseCodeSnippetComponent", getFileContent("templates/next/home-code-snippet-inverse.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT_TSX, "$generatedPath/components/home", onGeneratedFileListener)
        generateFile("HomePageCoverComponent", getFileContent("templates/next/home-cover.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT_TSX, "$generatedPath/components/home", onGeneratedFileListener)
        generateFile("ServicesComponent", getFileContent("templates/next/home-services.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT_TSX, "$generatedPath/components/home", onGeneratedFileListener)


        generateFile("PagesTitleConstants", getFileContent("templates/next/pages-title-constants.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT, "$generatedPath/content/constants", onGeneratedFileListener)
        generateFile("HomeServicesContent", getFileContent("templates/next/home-services-content.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT, "$generatedPath/content/content", onGeneratedFileListener)
        generateFile("ServiceModel", getFileContent("templates/next/service-model.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT, "$generatedPath/content/content/models", onGeneratedFileListener)

        generateFile("ApplicationColors", getFileContent("templates/next/colors.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT, "$generatedPath/content/utils", onGeneratedFileListener)
        generateFile("ApplicationStringsUtils", getFileContent("templates/next/app-snippet-example.txt").replace("#{Name}", projectName), FileExtention.TYPESCRIPT, "$generatedPath/content/utils", onGeneratedFileListener)
    }

}
