package generators.android.common

import generators.base.FileGenerator
import models.FileExtention

class AndroidApplicationDetailsGenerator constructor(
    private val packageName: String,
    private val generatedPath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String {
        return "AppDetails"
    }

    override fun getFileContent(): String {
        return "ext {\n" +
                "\n" +
                "    // Plugins\n" +
                "    AndroidPlugin = \"com.android.application\"\n" +
                "    KotlinAndroidPlugin = \"kotlin-android\"\n" +
                "    KotlinAndroidExPlugin = \"org.jetbrains.kotlin.android\"\n" +
                "    KotlinKaptPlugin = \"kotlin-kapt\"\n" +
                "    AndroidLibraryPlugin = \"com.android.library\"\n" +
                "    FirebasePlugin = \"com.google.gms.google-services\"\n" +
                "    navigationPlugin = \"androidx.navigation.safeargs.kotlin\"\n" +
                "    EasyLauncherPlugin = \"com.akaita.android.easylauncher\"\n" +
                "    RealmPlugin = \"realm-android\"\n" +
                "    Hilt = \"dagger.hilt.android.plugin\"\n" +
                "    CrashlyticsPlugin = \"com.google.firebase.crashlytics\"\n" +
                "\n" +
                "    //Android Details\n" +
                "    compileSdkV = 31\n" +
                "    BuildToolsVersion = \"29.0.0\"\n" +
                "    HttpLegacyEnabled = \"org.apache.http.legacy\"\n" +
                "    applicationPackage = \"${packageName}\"\n" +
                "    AndroidRunnerArgument = \"listener\"\n" +
                "    KotlinSource = \"src/main/kotlin\"\n" +
                "    EasyLauncherForground = \"@mipmap/ic_launcher_foreground\"\n" +
                "    LeakCanaryListener = \"com.squareup.leakcanary.FailTestOnLeakRunListener\"\n" +
                "    minSdkV = 24\n" +
                "    MultiDexMergeEnabled = true\n" +
                "    ApplicationVersion = \"1.0\"\n" +
                "    VersionCode = 1\n" +
                "    renderscriptTargetApi = 26\n" +
                "    renderscriptSupportModeEnabled = true\n" +
                "    AndroidTest = \"androidx.test.runner.AndroidJUnitRunner\"\n" +
                "    ApplicationBuildType = \"Testing\"\n" +
                "    ProguardFile = \"proguard-android-optimize.txt\"\n" +
                "    Proguard = \"proguard-rules.pro\"\n" +
                "}"
    }

    override fun getFileExt(): FileExtention = FileExtention.GRADLE
    override fun getFilePath(): String = generatedPath

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }
}