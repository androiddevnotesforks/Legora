package generators.android.common

import generators.base.FileGenerator
import models.FileExtention

class AndroidGradleProperitiesGenerator constructor(
    private val generatedFilePath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String {
        return "gradle"
    }

    override fun getFileContent(): String {
        return "# Project-wide Gradle settings.\n" +
                "# IDE (e.g. Android Studio) users:\n" +
                "# Gradle settings configured through the IDE *will override*\n" +
                "# any settings specified in this file.\n" +
                "# For more details on how to configure your build environment visit\n" +
                "# http://www.gradle.org/docs/current/userguide/build_environment.html\n" +
                "# Specifies the JVM arguments used for the daemon process.\n" +
                "# The setting is particularly useful for tweaking memory settings.\n" +
                "org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8\n" +
                "# When configured, Gradle will run in incubating parallel mode.\n" +
                "# This option should only be used with decoupled projects. More details, visit\n" +
                "# http://www.gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects\n" +
                "# org.gradle.parallel=true\n" +
                "# AndroidX package structure to make it clearer which packages are bundled with the\n" +
                "# Android operating system, and which are packaged with your app\"s APK\n" +
                "# https://developer.android.com/topic/libraries/support-library/androidx-rn\n" +
                "android.useAndroidX=true\n" +
                "# Kotlin code style for this project: \"official\" or \"obsolete\":\n" +
                "kotlin.code.style=official\n" +
                "# Enables namespacing of each library's R class so that its R class includes only the\n" +
                "# resources declared in the library itself and none from the library's dependencies,\n" +
                "# thereby reducing the size of the R class for that library\n" +
                "android.nonTransitiveRClass=true\n" +
                "android.enableJetifier=true\n" +
                "isGsm=true\n" +
                "android.jetifier.ignorelist=moshi-1.13.0"
    }

    override fun getFileExt(): FileExtention = FileExtention.PROPERTIES
    override fun getFilePath(): String = generatedFilePath

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }

}