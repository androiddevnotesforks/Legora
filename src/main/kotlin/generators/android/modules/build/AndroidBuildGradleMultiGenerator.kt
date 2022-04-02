package generators.android.modules.build

import generators.android.modules.app.AndroidAppBuildGradleFileGenerator
import generators.base.FileGenerator
import models.FileExtention

class AndroidBuildGradleMultiGenerator constructor(
    private val generatedPath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    companion object {
        private const val GRADLE_PLUGIN = "{rootProject.ext.PluginsVersions.GradlePluginV}"
        private const val KOTLIN_PLUGIN = "{rootProject.ext.PluginsVersions.KotlinPluginV}"
        private const val EASY_LAUNCHER_PLUGIN = "{rootProject.ext.PluginsVersions.EasyLauncherPluginV}"
        private const val FIREBASE_PLUGIN = "{rootProject.ext.PluginsVersions.FirebasePluginV}"
        private const val KTLINT_PLUGIN = "{rootProject.ext.PluginsVersions.KtlintPluginV}"
        private const val NAVIGATION_PLUGIN = "{rootProject.ext.PluginsVersions.NavigationPluginV}"
        private const val HILT_PLUGIN = "{rootProject.ext.PluginsVersions.HiltPluginV}"
        private const val FIREBASE_CRASHLYTICS = "{rootProject.ext.PluginsVersions.FirebaseCrashlyticsV}"

        const val PLUGIN_1 = "{rootProject.ext.AndroidPlugin}"
        const val PLUGIN_2 = "{rootProject.ext.KotlinAndroidPlugin}"
        const val PLUGIN_3 = "{rootProject.ext.KotlinAndroidExPlugin}"
        const val PLUGIN_4 = "{rootProject.ext.KotlinKaptPlugin}"

        const val BUILD_TOOLS = "{rootProject.ext.BuildToolsVersion}"
        const val HTTP_ENABLED = "{rootProject.ext.HttpLegacyEnabled}"
        const val PACKAGE_NAME = "{rootProject.ext.applicationPackage}"
        const val APP_VERSION = "{rootProject.ext.ApplicationVersion}"
        const val ANDROID_TEST = "{rootProject.ext.AndroidTest}"
        const val RUNNER_ARGS = "{rootProject.ext.AndroidRunnerArgument}"
        const val LEAK_CANARY = "{rootProject.ext.LeakCanaryListener}"
        const val PROJECT_SCHEMA = "projectDir/schemas"
        const val PROGUARD = "{rootProject.ext.ProguardFile}"
        const val PROGUARD_FILE = "{rootProject.ext.Proguard}"
    }

    override fun getFileName(): String {
        return "build"
    }

    override fun getFileContent(): String {
        return "buildscript {\n" +
                "\n" +
                "    ext {\n" +
                "        kotlin_version = '1.6.0'\n" +
                "    }\n" +
                "\n" +
                "    ext.PluginsVersions = [\n" +
                "            \"GradlePluginV\"        : \"4.2.0-alpha16\",\n" +
                "            \"KotlinPluginV\"        : \"1.6.0\",\n" +
                "            \"EasyLauncherPluginV\"  : \"1.3.1\",\n" +
                "            \"FirebasePluginV\"      : \"4.3.1\",\n" +
                "            \"KtlintPluginV\"        : \"2.1.1\",\n" +
                "            \"NavigationPluginV\"    : \"2.5.0-alpha01\",\n" +
                "            \"HiltPluginV\"          : \"2.38.1\",\n" +
                "            \"FirebaseCrashlyticsV\" : \"2.4.1\"\n" +
                "    ]\n" +
                "\n" +
                "    repositories {\n" +
                "        google()\n" +
                "        mavenCentral()\n" +
                "        maven { url \"https://plugins.gradle.org/m2/\" }\n" +
                "        maven { url 'https://jitpack.io' }\n" +
                "    }\n" +
                "\n" +
                "    dependencies {\n" +
                "        classpath \"com.android.tools.build:gradle:$$GRADLE_PLUGIN\"\n" +
                "        classpath \"org.jetbrains.kotlin:kotlin-gradle-plugin:$$KOTLIN_PLUGIN\"\n" +
                "        classpath \"com.akaita.android:easylauncher:$$EASY_LAUNCHER_PLUGIN\"\n" +
                "        classpath \"com.google.gms:google-services:$$FIREBASE_PLUGIN\"\n" +
                "        classpath \"org.jmailen.gradle:kotlinter-gradle:$$KTLINT_PLUGIN\"\n" +
                "        classpath \"androidx.navigation:navigation-safe-args-gradle-plugin:$$NAVIGATION_PLUGIN\"\n" +
                "        classpath \"com.google.dagger:hilt-android-gradle-plugin:$$HILT_PLUGIN\"\n" +
                "        classpath \"com.google.firebase:firebase-crashlytics-gradle:$$FIREBASE_CRASHLYTICS\"\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "\n" +
                "apply from: \"Libraries.gradle\"\n" +
                "apply from: \"AppDetails.gradle\"\n" +
                "\n" +
                "\n" +
                "subprojects {\n" +
                "    if (!project.name.contains(\"app\")) {\n" +
                "\n" +
                "        apply plugin: \"$$PLUGIN_1\"\n" +
                "        apply plugin: \"$$PLUGIN_2\"\n" +
                "        apply plugin: \"$$PLUGIN_3\"\n" +
                "        apply plugin: \"$$PLUGIN_4\"\n" +
                "\n" +
                "android {\n" +
                "\n" +
                "    compileSdkVersion rootProject.ext.compileSdkV\n" +
                "    buildToolsVersion \"$$BUILD_TOOLS\"\n" +
                "    useLibrary \"$$HTTP_ENABLED\"\n" +
                "\n" +
                "    defaultConfig {\n" +
                "        applicationId \"$$PACKAGE_NAME\"\n" +
                "        minSdkVersion rootProject.ext.minSdkV\n" +
                "        targetSdkVersion rootProject.ext.compileSdkV\n" +
                "        renderscriptTargetApi rootProject.ext.renderscriptTargetApi\n" +
                "        renderscriptSupportModeEnabled rootProject.ext.renderscriptSupportModeEnabled\n" +
                "        multiDexEnabled rootProject.ext.MultiDexMergeEnabled\n" +
                "        versionCode rootProject.ext.VersionCode\n" +
                "        versionName \"$$APP_VERSION\"\n" +
                "        testInstrumentationRunner \"$$ANDROID_TEST\"\n" +
                "        resConfigs \"en\", \"ar\"\n" +
                "        testInstrumentationRunnerArgument \"$${RUNNER_ARGS}\", \"$${LEAK_CANARY}\"\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "            buildTypes {\n" +
                "\n" +
                "        debug {\n" +
                "            minifyEnabled false\n" +
                "            debuggable true\n" +
                "            ext.enableCrashlytics = false\n" +
                "            ext.alwaysUpdateBuildId = false\n" +
                "            proguardFiles getDefaultProguardFile(\"$$PROGUARD\"), \"$$PROGUARD_FILE\"\n" +
                "        }\n" +
                "\n" +
                "        release {\n" +
                "            minifyEnabled true\n" +
                "            debuggable false\n" +
                "            ext.alwaysUpdateBuildId = false\n" +
                "            ext.enableCrashlytics = true\n" +
                "            proguardFiles getDefaultProguardFile(\"$$PROGUARD\"), \"$$PROGUARD_FILE\"\n" +
                "            // signingConfig signingConfigs.release\n" +
                "        }\n" +
                "\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        dependencies { config ->\n" +
                "            defaultTestingConfiguration(config)\n" +
                "            def RxConfiguration = rootProject.ext.RxConfiguration\n" +
                "            def KotlinCoroutinesConfiguration = rootProject.ext.KotlinCoroutinesConfiguration\n" +
                "\n" +
                "            implementation RxConfiguration.RxJava\n" +
                "            implementation RxConfiguration.RxAndroid\n" +
                "\n" +
                "            implementation KotlinCoroutinesConfiguration.Coroutines\n" +
                "            implementation KotlinCoroutinesConfiguration.AndroidCoroutines\n" +
                "\n" +
                "            daggerConfig(config)\n" +
                "\n" +
                "            if (project.name.equals(\"domain\")) {\n" +
                "                lifeCycleConfiguration(config)\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "}" +
                "\n" +
                "gradle.projectsEvaluated {\n" +
                "    tasks.withType(JavaCompile) {\n" +
                "        options.compilerArgs << \"-Xmaxerrs\" << \"500\"\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "void daggerConfig(configuration) {\n" +
                "    def DependenciesInjection = rootProject.ext.DependenciesInjection\n" +
                "    configuration.implementation DependenciesInjection.Dagger\n" +
                "    configuration.kapt DependenciesInjection.DaggerCompiler\n" +
                "    configuration.implementation DependenciesInjection.DaggerAndroid\n" +
                "    configuration.implementation DependenciesInjection.Hilt\n" +
                "    configuration.implementation DependenciesInjection.DaggerAndroidSupport\n" +
                "    configuration.kapt DependenciesInjection.DaggerAndroidProcessor\n" +
                "    configuration.kapt DependenciesInjection.HiltCompiler\n" +
                "}\n" +
                "\n" +
                "void defaultTestingConfiguration(configuration) {\n" +
                "    def TestingConfiguration = rootProject.ext.TestingConfiguration\n" +
                "    configuration.testImplementation TestingConfiguration.Junit\n" +
                "    configuration.androidTestImplementation TestingConfiguration.Esspresso\n" +
                "    configuration.androidTestImplementation TestingConfiguration.Runner\n" +
                "}\n" +
                "\n" +
                "void lifeCycleConfiguration(configuration) {\n" +
                "    def LifeCycleConfiguration = rootProject.ext.LifeCycleConfiguration\n" +
                "    configuration.implementation LifeCycleConfiguration.LifecycleViewModel\n" +
                "    configuration.implementation LifeCycleConfiguration.LifecycleExtensions\n" +
                "    configuration.implementation LifeCycleConfiguration.LifecycleRuntime\n" +
                "    configuration.implementation LifeCycleConfiguration.LifecycleLiveData\n" +
                "    configuration.implementation LifeCycleConfiguration.LifecycleCommon\n" +
                "    configuration.implementation LifeCycleConfiguration.LifeCycleSavedState\n" +
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