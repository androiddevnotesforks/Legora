package generators.android.common.modules.app

import generators.base.FileGenerator
import models.FileExtention

class AndroidAppBuildGradleFileGenerator constructor(
    private val generatedFilePath: String,
    private val onMessageGenerated: (String) -> Unit
): FileGenerator() {
    
    companion object {
        const val PLUGIN_1 = "{rootProject.ext.AndroidPlugin}"
        const val PLUGIN_2 = "{rootProject.ext.KotlinAndroidPlugin}"
        const val PLUGIN_3 = "{rootProject.ext.KotlinAndroidExPlugin}"
        const val PLUGIN_4 = "{rootProject.ext.KotlinKaptPlugin}"
        const val PLUGIN_5 = "{rootProject.ext.navigationPlugin}"
        const val PLUGIN_6 = "{rootProject.ext.Hilt}"
        const val PLUGIN_7 = "{rootProject.ext.FirebasePlugin}"
        const val PLUGIN_8 = "{rootProject.ext.CrashlyticsPlugin}"

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
        const val IS_GSM = "{isGsm}"
        const val KOTLIN_SOURCE = "{rootProject.ext.KotlinSource}"
        const val PROFILE_NAME = "{profileName}"
        const val APP_NAME = "{appName}"
        const val APP_TYPE = "{rootProject.ext.ApplicationBuildType}"
        const val VERSION_NAME = "{versionName}"
        const val FIREBASE_PLATFORM = "{firebaseConfiguration.FirebasePlatform}"
    }
    
    override fun getFileName(): String {
        return "build"
    }

    override fun getFileContent(): String {
        return "apply plugin: \"$$PLUGIN_1\"\n" +
                "apply plugin: \"$$PLUGIN_2\"\n" +
                "apply plugin: \"$$PLUGIN_3\"\n" +
                "apply plugin: \"$$PLUGIN_4\"\n" +
                "apply plugin: \"$$PLUGIN_5\"\n" +
                "apply plugin: \"$$PLUGIN_6\"\n" +
                "apply plugin: \"$$PLUGIN_7\"\n" +
                "apply plugin: \"$$PLUGIN_8\"\n" +
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
                "        testInstrumentationRunnerArgument \"$$RUNNER_ARGS\", \"$$LEAK_CANARY\"\n" +
                "        manifestPlaceholders = [\n" +
                "                enableCrashReporting: \"false\",\n" +
                "                CrashReportingApiKey: \"ApiKeyHere\"\n" +
                "        ]\n" +
                "\n" +
                "        javaCompileOptions {\n" +
                "            annotationProcessorOptions {\n" +
                "                arguments += [\"room.schemaLocation\": \"$$PROJECT_SCHEMA\".toString()]\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    lintOptions {\n" +
                "        abortOnError false\n" +
                "    }\n" +
                "\n" +
                "    buildTypes {\n" +
                "\n" +
                "        debug {\n" +
                "            minifyEnabled false\n" +
                "            debuggable true\n" +
                "            ext.enableCrashlytics = false\n" +
                "            ext.alwaysUpdateBuildId = false\n" +
                "            proguardFiles getDefaultProguardFile(\"$$PROGUARD\"), \"$$PROGUARD_FILE\"\n" +
                "            buildConfigField \"String\", \"IS_GSM\", \"\\\"$$\\\"\"\n" +
                "        }\n" +
                "\n" +
                "        release {\n" +
                "            minifyEnabled true\n" +
                "            debuggable false\n" +
                "            ext.alwaysUpdateBuildId = false\n" +
                "            ext.enableCrashlytics = true\n" +
                "            proguardFiles getDefaultProguardFile(\"$$PROGUARD\"), \"$$PROGUARD_FILE\"\n" +
                "            buildConfigField \"String\", \"IS_GSM\", \"\\\"$$IS_GSM\\\"\"\n" +
                "            // signingConfig signingConfigs.release\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    sourceSets {\n" +
                "        main.java.srcDirs += \"$$KOTLIN_SOURCE\"\n" +
                "    }\n" +
                "\n" +
                "    lintOptions {\n" +
                "        checkReleaseBuilds false\n" +
                "    }\n" +
                "\n" +
                "    testOptions {\n" +
                "        unitTests {\n" +
                "            includeAndroidResources = true\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    lintOptions {\n" +
                "        disable 'ContentDescription',\n" +
                "                'ObsoleteLintCustomCheck',\n" +
                "                'CheckResult'\n" +
                "    }\n" +
                "\n" +
                "    applicationVariants.all { variant ->\n" +
                "        def profileName\n" +
                "        if (getGradle().getStartParameter().getTaskRequests().toString().contains(\"Debug\")) {\n" +
                "            profileName = \"-Debug Version\"\n" +
                "        } else {\n" +
                "            profileName = \"-Release Version\"\n" +
                "        }\n" +
                "        def appName = \"League $$PROFILE_NAME\"\n" +
                "        variant.outputs.all { output ->\n" +
                "            def newApkName\n" +
                "            newApkName = \"$$APP_NAME-$$APP_TYPE -$$VERSION_NAME.apk\"\n" +
                "            output.outputFileName = new File(newApkName)\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    compileOptions {\n" +
                "        sourceCompatibility JavaVersion.VERSION_1_8\n" +
                "        targetCompatibility JavaVersion.VERSION_1_8\n" +
                "    }\n" +
                "\n" +
                "    kotlinOptions {\n" +
                "        jvmTarget = '1.8'\n" +
                "    }\n" +
                "\n" +
                "    buildFeatures {\n" +
                "        viewBinding true\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "\n" +
                "dependencies { configuration ->\n" +
                "    implementation fileTree(dir: 'libs', include: ['*.jar'])\n" +
                "    def firebaseConfiguration = rootProject.ext.FirebaseConfiguration\n" +
                "    implementation platform(\"$$FIREBASE_PLATFORM\")\n" +
                "\n" +
                "    defaultTestingConfiguration(configuration)\n" +
                "    googlePlayServicesConfiguration(configuration)\n" +
                "    rxJavaConfiguration(configuration)\n" +
                "    kotlinCoroutines(configuration)\n" +
                "    userInterfaceConfiguration(configuration)\n" +
                "    retrofitConfiguration(configuration)\n" +
                "    applicationConfiguration(configuration)\n" +
                "    daggerConfig(configuration)\n" +
                "    firebaseConfiguration(configuration)\n" +
                "    moshiConfiguration(configuration)\n" +
                "    roomConfiguration(configuration)\n" +
                "    guavaConfiguration(configuration)\n" +
                "    workManagerConfiguration(configuration)\n" +
                "}\n" +
                "\n" +
                "private void workManagerConfiguration(configuration) {\n" +
                "    def WorkManagerConfiguration = rootProject.ext.WorkManagerConfiguration\n" +
                "    configuration.implementation WorkManagerConfiguration.WorkManager\n" +
                "    configuration.implementation WorkManagerConfiguration.WorkManagerRunTime\n" +
                "    configuration.implementation WorkManagerConfiguration.Gcm\n" +
                "}\n" +
                "\n" +
                "private void guavaConfiguration(configuration) {\n" +
                "    def GuavaConfiguration = rootProject.ext.GuavaConfiguration\n" +
                "    configuration.implementation GuavaConfiguration.Guava\n" +
                "    configuration.implementation GuavaConfiguration.GuavaAndroid\n" +
                "    configuration.api GuavaConfiguration.GuavaApi\n" +
                "    configuration.api GuavaConfiguration.GuavaAndroidApi\n" +
                "}\n" +
                "\n" +
                "private void roomConfiguration(configuration) {\n" +
                "    def RoomConfiguration = rootProject.ext.RoomConfiguration\n" +
                "    configuration.implementation RoomConfiguration.RoomRunTime\n" +
                "    configuration.implementation RoomConfiguration.RoomPaging\n" +
                "    configuration.kapt RoomConfiguration.RoomCompiler\n" +
                "}\n" +
                "\n" +
                "private void moshiConfiguration(configuration) {\n" +
                "    def MoshiConfiguration = rootProject.ext.MoshiConfiguration\n" +
                "    configuration.implementation MoshiConfiguration.MoshiRetrofitAdapter\n" +
                "    configuration.implementation MoshiConfiguration.RetrofitCorotinesAdapter\n" +
                "    configuration.kapt MoshiConfiguration.JvmMetaData\n" +
                "    configuration.kapt MoshiConfiguration.MoshiCodegen\n" +
                "}\n" +
                "\n" +
                "private void firebaseConfiguration(configuration) {\n" +
                "    configuration.implementation FirebaseConfiguration.FirebaseCrashlytics\n" +
                "    configuration.implementation FirebaseConfiguration.FirebaseAnalytics\n" +
                "    configuration.implementation FirebaseConfiguration.FirebaseMessaging\n" +
                "    configuration.implementation FirebaseConfiguration.FirebaseFunctions\n" +
                "}\n" +
                "\n" +
                "private void googlePlayServicesConfiguration(configuration) {\n" +
                "    def GooglePlayServices = rootProject.ext.GooglePlayServices\n" +
                "    configuration.implementation GooglePlayServices.GoogleMaps\n" +
                "    configuration.implementation GooglePlayServices.GooglePlaces\n" +
                "    configuration.implementation GooglePlayServices.GoogleGcm\n" +
                "    configuration.implementation GooglePlayServices.GoogleLocations\n" +
                "}\n" +
                "\n" +
                "private void rxJavaConfiguration(configuration) {\n" +
                "    def RxConfiguration = rootProject.ext.RxConfiguration\n" +
                "    configuration.implementation RxConfiguration.RxJava\n" +
                "    configuration.implementation RxConfiguration.RxAndroid\n" +
                "}\n" +
                "\n" +
                "private void kotlinCoroutines(configuration) {\n" +
                "    def KotlinCoroutinesConfiguration = rootProject.ext.KotlinCoroutinesConfiguration\n" +
                "    configuration.implementation KotlinCoroutinesConfiguration.Coroutines\n" +
                "    configuration.implementation KotlinCoroutinesConfiguration.AndroidCoroutines\n" +
                "}\n" +
                "\n" +
                "private void userInterfaceConfiguration(configuration) {\n" +
                "    def UiConfiguration = rootProject.ext.UiConfiguration\n" +
                "    configuration.implementation UiConfiguration.RecyclerView\n" +
                "    configuration.implementation UiConfiguration.SupportCompat\n" +
                "    configuration.implementation UiConfiguration.SupportCore\n" +
                "    configuration.implementation UiConfiguration.SupportCoreUi\n" +
                "    configuration.implementation UiConfiguration.SupportFragment\n" +
                "    configuration.implementation UiConfiguration.AndroidCore\n" +
                "    configuration.implementation UiConfiguration.CardView\n" +
                "    configuration.implementation UiConfiguration.AppCompat\n" +
                "    configuration.implementation UiConfiguration.GoogleMaterial\n" +
                "    configuration.implementation UiConfiguration.Constraintlayout\n" +
                "    configuration.implementation UiConfiguration.NavigationFragment\n" +
                "    configuration.implementation UiConfiguration.NavigationUI\n" +
                "    configuration.implementation UiConfiguration.FragmentKtx\n" +
                "    configuration.implementation UiConfiguration.Glide\n" +
                "    configuration.kapt UiConfiguration.GlideAnnotations\n" +
                "}\n" +
                "\n" +
                "private void retrofitConfiguration(configuration) {\n" +
                "    def DataConfiguration = rootProject.ext.DataConfiguration\n" +
                "    configuration.implementation DataConfiguration.Retrofit\n" +
                "    configuration.implementation DataConfiguration.RetrofitRxJavaAdapter\n" +
                "    configuration.implementation DataConfiguration.RetrofitIntercenptor\n" +
                "    configuration.implementation DataConfiguration.retrofitGsonConverter\n" +
                "    configuration.implementation DataConfiguration.Moshi\n" +
                "}\n" +
                "\n" +
                "private void applicationConfiguration(configuration) {\n" +
                "    def ApplicationConfiguration = rootProject.ext.ApplicationConfiguration\n" +
                "    configuration.implementation ApplicationConfiguration.Timber\n" +
                "    configuration.implementation ApplicationConfiguration.MultiDexApp\n" +
                "    configuration.implementation ApplicationConfiguration.KotlinJdk\n" +
                "}"
    }

    override fun getFileExt(): FileExtention = FileExtention.GRADLE
    override fun getFilePath(): String = generatedFilePath

    override fun execute() {
        generateFile {

        }
    }


}