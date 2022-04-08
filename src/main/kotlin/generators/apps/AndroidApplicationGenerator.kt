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
        generateModule("app", packageName, projectName)
        if (!isSingleModuleApplication) {
            generateModule("data", packageName, projectName)
            generateModule("domain", packageName, projectName)
        }
    }

    private fun generateGradleDirectory() {
        generateDirectory("$generatedPath/gradle", onGeneratedFileListener)
        generateDirectory("$generatedPath/gradle/wrapper", onGeneratedFileListener)
        generateFile("gradle-wrapper", getFileContent("templates/gradle/android-gradle-wrapper.txt"), FileExtention.PROPERTIES, "$generatedPath/gradle/wrapper", onGeneratedFileListener)
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

    private fun generateModule(name: String, packageName: String, projectName: String) {
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
        generateDirectory("$generatedPath/$name/src/main/java$createdPackageName/$name/screens", onGeneratedFileListener)
        generateDirectory("$generatedPath/$name/src/main/java$createdPackageName/$name/utils", onGeneratedFileListener)

        // Kotlin Classes
        generateFile(projectName + "Application", getFileContent("templates/android/android-app-class.txt").replace("#{Package}", packageName).replace("#{Name}", projectName), FileExtention.KOTLIN, "$generatedPath/$name/src/main/java$createdPackageName/$name", onGeneratedFileListener)
        generateFile("SplashScreen", getFileContent("templates/android/android-splash-screen.txt").replace("#{Package}", packageName), FileExtention.KOTLIN, "$generatedPath/$name/src/main/java$createdPackageName/$name/screens", onGeneratedFileListener)
        generateFile("ApplicationLinkScreen", getFileContent("templates/android/android-splash-screen.txt").replace("#{Package}", packageName), FileExtention.KOTLIN, "$generatedPath/$name/src/main/java$createdPackageName/$name/screens", onGeneratedFileListener)
        generateFile("ApplicationLinkScreen", getFileContent("templates/android/android-timber.txt").replace("#{Package}", packageName), FileExtention.KOTLIN, "$generatedPath/$name/src/main/java$createdPackageName/$name/utils", onGeneratedFileListener)

        // Generate Root Files
        generateFile("build", getFileContent("templates/gradle/modules-builds/$name-build-gradle.txt").replace("#{Name}", projectName), FileExtention.GRADLE, "$generatedPath/$name", onGeneratedFileListener)
        generateFile("proguard-rules", getFileContent("templates/configurations/android-proguard-rules.txt"), FileExtention.PRO, "$generatedPath/$name", onGeneratedFileListener)
        generateFile("AndroidManifest", getFileContent("templates/xml/$name-android-manifest.txt").replace("#{Name}", "$packageName.$name"), FileExtention.XML, "$generatedPath/$name/src/main", onGeneratedFileListener)
        if (name.equals("app")) {
            generateFile("google-services", getFileContent("templates/configurations/android-google-services.txt"), FileExtention.JSON, "$generatedPath/$name", onGeneratedFileListener)
            generateValuesFiles(projectName)
        }
    }

    private fun generateValuesFiles(projectName: String) {
        generateFile("splash_screen_background", getFileContent("templates/android/android-splash-screen-theme.txt"), FileExtention.XML, "$generatedPath/app/src/main/res/drawable", onGeneratedFileListener)
        generateFile("activity_main", getFileContent("templates/android/main-screen-layout.txt"), FileExtention.XML, "$generatedPath/app/src/main/res/layout", onGeneratedFileListener)

        generateFile("colors", getFileContent("templates/android/android-colors.txt"), FileExtention.XML, "$generatedPath/app/src/main/res/values", onGeneratedFileListener)
        generateFile("colors", getFileContent("templates/android/android-colors.txt"), FileExtention.XML, "$generatedPath/app/src/main/res/values-night", onGeneratedFileListener)

        generateFile("themes", getFileContent("templates/android/android-theme.txt"), FileExtention.XML, "$generatedPath/app/src/main/res/values", onGeneratedFileListener)
        generateFile("themes", getFileContent("templates/android/android-theme.txt"), FileExtention.XML, "$generatedPath/app/src/main/res/values-night", onGeneratedFileListener)

        generateFile("strings", getFileContent("templates/android/android-strings.txt").replace("#{Name}", projectName), FileExtention.XML, "$generatedPath/app/src/main/res/values", onGeneratedFileListener)
        generateFile("strings", getFileContent("templates/android/android-strings.txt").replace("#{Name}", projectName), FileExtention.XML, "$generatedPath/app/src/main/res/values-ar", onGeneratedFileListener)
    }

}
