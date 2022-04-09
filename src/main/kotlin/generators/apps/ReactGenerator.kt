package generators.apps

import generators.base.ProjectGeneratorImplementation
import generators.base.TemplatingFilesGenerator
import models.ApplicationDependency
import models.FileExtention
import models.ProjectInformationItem

class ReactGenerator constructor(
    private val isJavaScriptLanguage: Boolean,
    private val generatedPath: String,
    private val onGeneratedFileListener: (String) -> Unit
): TemplatingFilesGenerator(), ProjectGeneratorImplementation {

    override fun generateProject(dependencies: ArrayList<ApplicationDependency>, fields: HashMap<String, String>) {
        val name = fields[ProjectInformationItem.NAME] ?: ""
        val author = fields[ProjectInformationItem.AUTHER] ?: ""
        val description = fields[ProjectInformationItem.DESCRIPTION] ?: ""
        val website = fields[ProjectInformationItem.WEBSITE] ?: ""
        val version = fields[ProjectInformationItem.VERSION] ?: ""

        generateDirectories()
        generateRootFiles()

        generateFile("package",
            getFileContent("templates/configurations/react-package.json")
                .replace("#{Name}", name)
                .replace("#{Description}", description)
                .replace("#{Author}", author)
                .replace("#{Website}", website)
                .replace("#{Version}", version)
            , FileExtention.JSON,
            generatedPath,
            onGeneratedFileListener
        )

        generateSrcFiles()
    }

    private fun generateRootFiles() {
        generateFile("webpack.config", getFileContent("templates/configurations/webpack.config.js"), FileExtention.JAVASCRIPT, generatedPath, onGeneratedFileListener)
        generateFile("tailwind.config", getFileContent("templates/configurations/tailwind.config.js"), FileExtention.JAVASCRIPT, generatedPath, onGeneratedFileListener)
        generateFile("firebase", getFileContent("templates/configurations/firebase.json"), FileExtention.JSON, generatedPath, onGeneratedFileListener)
        generateFile("docker-compose", getFileContent("templates/configurations/docker-compose.yml"), FileExtention.YML, generatedPath, onGeneratedFileListener)
        generateFile("craco.config", getFileContent("templates/configurations/craco.config.js"), FileExtention.JAVASCRIPT, generatedPath, onGeneratedFileListener)
        generateFile("Dockerfile", getFileContent("templates/configurations/Dockerfile.dev"), FileExtention.DEV, generatedPath, onGeneratedFileListener)
        generateFile(".gitignore", getFileContent("templates/gitignore/react-gitignore.txt"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
        generateFile(".dockerignore", getFileContent("templates/gitignore/.dockerignore.txt"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
        generateFile(".env", getFileContent("templates/configurations/react-.env.txt"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
    }

    private fun generateDirectories() {
        generateDirectory("$generatedPath/public", onGeneratedFileListener)
        generateDirectory("$generatedPath/src", onGeneratedFileListener)

        generateDirectory("$generatedPath/src/components", onGeneratedFileListener)
        generateDirectory("$generatedPath/src/info", onGeneratedFileListener)
        generateDirectory("$generatedPath/src/styles", onGeneratedFileListener)

        generateDirectory("$generatedPath/src/styles/pages", onGeneratedFileListener)
        generateDirectory("$generatedPath/src/styles/components", onGeneratedFileListener)

        generateDirectory("$generatedPath/src/components/home", onGeneratedFileListener)
        generateDirectory("$generatedPath/src/components/common", onGeneratedFileListener)
        generateDirectory("$generatedPath/src/components/styles", onGeneratedFileListener)
    }

    private fun generateSrcFiles() {
        val generateSource = "$generatedPath/src"
        generateFile("index", getFileContent("templates/react/index.html"), FileExtention.HTML, "$generatedPath/public", onGeneratedFileListener)
        generateFile("robots", getFileContent("templates/react/robots.txt"), FileExtention.TXT, "$generatedPath/public", onGeneratedFileListener)
        generateFile("manifest", getFileContent("templates/react/manifest.json"), FileExtention.JSON, "$generatedPath/public", onGeneratedFileListener)

        generateFile("index", getFileContent("templates/css/react-index.css"), FileExtention.CSS, generateSource, onGeneratedFileListener)
        generateFile("App", getFileContent("templates/react/App.test.js"), FileExtention.JAVASCRIPT, generateSource, onGeneratedFileListener)
        generateFile("index", getFileContent("templates/react/index.js"), FileExtention.JAVASCRIPT, generateSource, onGeneratedFileListener)
        generateFile("App", getFileContent("templates/react/App.scss"), FileExtention.SCSS, generateSource, onGeneratedFileListener)
        generateFile("App", getFileContent("templates/react/App.js"), FileExtention.JAVASCRIPT, generateSource, onGeneratedFileListener)

        generateFile("animations", getFileContent("templates/css/react-styles/animations.scss"), FileExtention.SCSS, "$generatedPath/src/styles", onGeneratedFileListener)
        generateFile("base", getFileContent("templates/css/react-styles/base.scss"), FileExtention.SCSS, "$generatedPath/src/styles", onGeneratedFileListener)
        generateFile("mixins", getFileContent("templates/css/react-styles/mixins.scss"), FileExtention.SCSS, "$generatedPath/src/styles", onGeneratedFileListener)
        generateFile("utils", getFileContent("templates/css/react-styles/utils.scss"), FileExtention.SCSS, "$generatedPath/src/styles", onGeneratedFileListener)
        generateFile("variables", getFileContent("templates/css/react-styles/variables.scss"), FileExtention.SCSS, "$generatedPath/src/styles", onGeneratedFileListener)

        generateFile("ColorUtils", getFileContent("templates/react/info/color-utils.js"), FileExtention.JAVASCRIPT, "$generatedPath/src/info", onGeneratedFileListener)
        generateFile("ServicesList", getFileContent("templates/react/info/services.js"), FileExtention.JAVASCRIPT, "$generatedPath/src/info", onGeneratedFileListener)
        generateFile("StringsUtils", getFileContent("templates/react/info/strings.js"), FileExtention.JAVASCRIPT, "$generatedPath/src/info", onGeneratedFileListener)

        generateFile("HomePageComponent", getFileContent("templates/react/code/HomePageComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components", onGeneratedFileListener)
        generateFile("NotFoundPageViewComponent", getFileContent("templates/react/code/NotFoundPageViewComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components", onGeneratedFileListener)
        generateFile("MobilePagesNavigationComponent", getFileContent("templates/react/code/MobilePagesNavigationComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components", onGeneratedFileListener)

        generateFile("FooterComponent", getFileContent("templates/react/code/FooterComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components/common", onGeneratedFileListener)
        generateFile("InnerToolbarComponent", getFileContent("templates/react/code/InnerToolbarComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components/common", onGeneratedFileListener)
        generateFile("ScreenContainerComponent", getFileContent("templates/react/code/ScreenContainerComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components/common", onGeneratedFileListener)
        generateFile("ToolbarComponent", getFileContent("templates/react/code/ToolbarComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components/common", onGeneratedFileListener)

        generateFile("HomePageCoverComponent", getFileContent("templates/react/code/HomePageCoverComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components/home", onGeneratedFileListener)
        generateFile("ServicesComponent", getFileContent("templates/react/code/ServicesComponent.js"), FileExtention.JAVASCRIPT_JSX, "$generatedPath/src/components/home", onGeneratedFileListener)

        generateFile("HomePageStyle", getFileContent("templates/css/react/HomePageStyle.scss"), FileExtention.SCSS, "$generatedPath/src/components/styles/pages", onGeneratedFileListener)
        generateFile("ToolbarStyle", getFileContent("templates/css/react/ToolbarStyle.scss"), FileExtention.SCSS, "$generatedPath/src/components/styles/components", onGeneratedFileListener)
    }

}
