package generators.apps

import generators.base.ProjectGeneratorImplementation
import generators.base.TemplatingFilesGenerator
import models.ApplicationDependency
import models.FileExtention
import models.ProjectInformationItem

class AndroidApplicationGenerator constructor(
    private val generatedPath: String,
    private val isSingleModuleApplication: Boolean,
    private val onGeneratedFileListener: (String) -> Unit
): TemplatingFilesGenerator(), ProjectGeneratorImplementation {

    override fun generateProject(dependencies: ArrayList<ApplicationDependency>, fields: HashMap<String, String>) {
        val packageName = fields[ProjectInformationItem.PACKAGE] ?: "me.legora"
        val projectName = fields[ProjectInformationItem.NAME] ?: "Legora Application"
        generateGradleDirectory()
        generateRootFiles(packageName)
        generateSettingsFile(projectName)
        generateModule("app", packageName)
        if (!isSingleModuleApplication) {
            generateModule("data", packageName)
            generateModule("domain", packageName)
        }
    }

    private fun generateGradleDirectory() {
        generateDirectory("$generatedPath/gradle", onGeneratedFileListener)
        generateDirectory("$generatedPath/gradle/wrapper", onGeneratedFileListener)
        generateFile("Libraries", getFileContent("templates/gradle/android-gradle-wrapper.txt"), FileExtention.PROPERTIES, "$generatedPath/gradle/wrapper", onGeneratedFileListener)
    }

    private fun generateSettingsFile(name: String) {
        val fileContent = if (isSingleModuleApplication) {
            getFileContent("templates/gradle/android-settings-single-module.txt")
        } else {
            getFileContent("templates/gradle/android-settings-multi-module.txt")
        }

        fileContent.replace("#{Name}", name)
        generateFile("settings", fileContent, FileExtention.GRADLE, generatedPath, onGeneratedFileListener)
    }

    private fun generateRootFiles(packageName: String) {
        generateFile("gradlew", getFileContent("templates/gradle/gradle-runner-android.txt"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
        generateFile("gradlew", getFileContent("templates/gradle/gradlew-bat.txt"), FileExtention.BAT, generatedPath, onGeneratedFileListener)
        generateFile("AppDetails", getFileContent("templates/gradle/android-app-details.txt").replace("#{PackageName}", packageName), FileExtention.GRADLE, generatedPath, onGeneratedFileListener)
        generateFile("gradle", getFileContent("templates/gradle/gradle-props.txt"), FileExtention.PROPERTIES, generatedPath, onGeneratedFileListener)
        generateFile("Libraries", getFileContent("templates/gradle/android-app-libraries.txt"), FileExtention.GRADLE, generatedPath, onGeneratedFileListener)
        if (isSingleModuleApplication) {
            generateFile("build", getFileContent("templates/gradle/android-single-module-build-root.txt"), FileExtention.GRADLE, generatedPath, onGeneratedFileListener)
        } else {
            generateFile("build", getFileContent("templates/gradle/android-multi-module-build-root.txt"), FileExtention.GRADLE, generatedPath, onGeneratedFileListener)
        }
    }

    private fun generateModule(name: String, packageName: String) {
        generateDirectory("$generatedPath/$name", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src", onGeneratedFileListener)

        generateDirectory("$generatedPath/$name/androidTest", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/test", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main", onGeneratedFileListener)

        generateDirectory("$generatedPath/$name/src/main/java", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res", onGeneratedFileListener)

        generateDirectory("$generatedPath/$name/src/main/res/drawable", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/drawable-v24", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/drawable-mdpi", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/drawable-hdpi", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/drawable-nodpi", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/drawable-xhdpi", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/drawable-xxhdpi", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/drawable-xxxhdpi", onGeneratedFileListener)

        generateDirectory("$generatedPath/$name/src/main/res/values", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/values-night", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/values-ar", onGeneratedFileListener)

        generateDirectory("$generatedPath/$name/src/main/res/layout", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/layout-v21", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/res/layout-v24", onGeneratedFileListener)

        var createdPackageName = ""
        val packageNameFragments = packageName.split(".")
        packageNameFragments.forEach {
            createdPackageName += "/$it"
            generateDirectory("$generatedPath/$name/src/main/java$createdPackageName", onGeneratedFileListener)
        }

        generateDirectory("$generatedPath/$name/src/main/java$createdPackageName/$name", onGeneratedFileListener)
    }

}
