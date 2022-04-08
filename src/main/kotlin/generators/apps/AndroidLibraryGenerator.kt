package generators.apps

import generators.base.ProjectGeneratorImplementation
import generators.base.TemplatingFilesGenerator
import models.ApplicationDependency
import models.FileExtention
import models.ProjectInformationItem

class AndroidLibraryGenerator constructor(
    private val generatedFilePath: String,
    private val onGeneratedFileListener: (String) -> Unit
): TemplatingFilesGenerator(), ProjectGeneratorImplementation {

    override fun generateProject(dependencies: ArrayList<ApplicationDependency>, fields: HashMap<String, String>) {
        val projectName = fields[ProjectInformationItem.NAME] ?: ""
        val projectPackageName = fields[ProjectInformationItem.PACKAGE] ?: ""
        val artifact = fields[ProjectInformationItem.ARTIFACE] ?: ""
        val version = fields[ProjectInformationItem.VERSION] ?: ""
        val group = fields[ProjectInformationItem.GROUP] ?: ""
        val description = fields[ProjectInformationItem.DESCRIPTION] ?: ""
        val gitLink = fields[ProjectInformationItem.GIT_LINK] ?: ""
        val repoName = fields[ProjectInformationItem.REPO_NAME] ?: ""
        generateRootFiles(projectName)
        generateGradleDirectory()
        generateDirectories()
        generateModule("app", projectPackageName, projectName, version, artifact, repoName, gitLink, description, group)
        generateModule("library", projectPackageName, projectName, version, artifact, repoName, gitLink, description, group)
        generateFile(
            "build",
            getFileContent("templates/gradle/library-build-gradle.txt")
                .replace("#{Group}", group)
                .replace("#{Artifact}", artifact)
                .replace("#{Version}", version),
            FileExtention.GRADLE,
            generatedFilePath,
            onGeneratedFileListener
        )
    }

    private fun generateModule(
        name: String,
        packageName: String,
        projectName: String,
        version: String,
        artifact: String,
        repoName: String,
        gitLink: String,
        description: String,
        group: String
    ) {
        generateDirectory("$generatedFilePath/$name", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src", onGeneratedFileListener)

        generateDirectory("$generatedFilePath/$name/androidTest", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/test", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main", onGeneratedFileListener)

        generateDirectory("$generatedFilePath/$name/src/main/java", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res", onGeneratedFileListener)

        generateDirectory("$generatedFilePath/$name/src/main/res/drawable", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/drawable-v24", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/drawable-mdpi", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/drawable-hdpi", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/drawable-nodpi", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/drawable-xhdpi", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/drawable-xxhdpi", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/drawable-xxxhdpi", onGeneratedFileListener)

        generateDirectory("$generatedFilePath/$name/src/main/res/values", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/values-night", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/values-ar", onGeneratedFileListener)

        generateDirectory("$generatedFilePath/$name/src/main/res/layout", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/layout-v21", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/$name/src/main/res/layout-v24", onGeneratedFileListener)

        var createdPackageName = ""
        val packageNameFragments = packageName.split(".")
        packageNameFragments.forEach {
            createdPackageName += "/$it"
            generateDirectory("$generatedFilePath/$name/src/main/java$createdPackageName", onGeneratedFileListener)
        }

        // Generate Root Files
        generateFile("build", getFileContent("templates/gradle/modules-builds/$name-build-gradle.txt")
            .replace("#{Name}", projectName)
            .replace("#{Version}", version)
            .replace("#{Artifact}", artifact)
            .replace("#{GitLink}", gitLink)
            .replace("#{Description}", description)
            .replace("#{Group}", group)
            .replace("#{RepoName}", repoName)
            , FileExtention.GRADLE, "$generatedFilePath/$name", onGeneratedFileListener)

        // Kotlin Classes
        generateFile(projectName + "Application", getFileContent("templates/android/android-app-class.txt").replace("#{Package}", packageName).replace("#{Name}", projectName), FileExtention.KOTLIN, "$generatedFilePath/$name/src/main/java$createdPackageName/$name", onGeneratedFileListener)
        generateFile("SplashScreen", getFileContent("templates/android/android-splash-screen.txt").replace("#{Package}", packageName), FileExtention.KOTLIN, "$generatedFilePath/$name/src/main/java$createdPackageName/$name/screens", onGeneratedFileListener)
        generateFile("ApplicationLinkScreen", getFileContent("templates/android/android-splash-screen.txt").replace("#{Package}", packageName), FileExtention.KOTLIN, "$generatedFilePath/$name/src/main/java$createdPackageName/$name/screens", onGeneratedFileListener)
        generateFile("ApplicationLinkScreen", getFileContent("templates/android/android-timber.txt").replace("#{Package}", packageName), FileExtention.KOTLIN, "$generatedFilePath/$name/src/main/java$createdPackageName/$name/utils", onGeneratedFileListener)

        generateFile("proguard-rules", getFileContent("templates/configurations/android-proguard-rules.txt"), FileExtention.PRO, "$generatedFilePath/$name", onGeneratedFileListener)
        generateFile("AndroidManifest", getFileContent("templates/xml/$name-android-manifest.txt").replace("#{Name}", "$packageName.$name"), FileExtention.XML, "$generatedFilePath/$name/src/main", onGeneratedFileListener)
    }

    private fun generateDirectories() {
        generateDirectory("$generatedFilePath/scripts", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/library", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/app", onGeneratedFileListener)
    }

    private fun generateRootFiles(name: String) {
        generateFile("settings", getFileContent("templates/gradle/gradle-library-settings.txt").replace("#{Name}", name), FileExtention.GRADLE, generatedFilePath, onGeneratedFileListener)
        generateFile("gradlew", getFileContent("templates/gradle/gradle-runner-android.txt"), FileExtention.EMPTY, generatedFilePath, onGeneratedFileListener)
        generateFile("gradlew", getFileContent("templates/gradle/gradlew-bat.txt"), FileExtention.BAT, generatedFilePath, onGeneratedFileListener)
        generateFile("gradle", getFileContent("templates/gradle/gradle-props.txt"), FileExtention.PROPERTIES, generatedFilePath, onGeneratedFileListener)
        generateFile(".gitignore", getFileContent("templates/gitignore/android-library.txt"), FileExtention.EMPTY, generatedFilePath, onGeneratedFileListener)
    }

    private fun generateGradleDirectory() {
        generateDirectory("$generatedFilePath/gradle", onGeneratedFileListener)
        generateDirectory("$generatedFilePath/gradle/wrapper", onGeneratedFileListener)
        generateFile("gradle-wrapper", getFileContent("templates/gradle/android-gradle-wrapper.txt"), FileExtention.PROPERTIES, "$generatedFilePath/gradle/wrapper", onGeneratedFileListener)
    }

}