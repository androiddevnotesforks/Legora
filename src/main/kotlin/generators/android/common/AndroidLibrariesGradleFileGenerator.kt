package generators.android.common

import generators.base.FileGenerator
import models.FileExtention

class AndroidLibrariesGradleFileGenerator constructor(
    private val generatedFilePath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {
    
    override fun getFileName(): String {
        return "Libraries"
    }

    override fun getFileContent(): String {
        return "ext {\n" +
                "\n" +
                "    ApplicationConfiguration = [\n" +
                "            Timber                   : \"com.jakewharton.timber:timber:4.7.1\",\n" +
                "            MultiDexApp              : \"com.android.support:multidex:1.0.3\",\n" +
                "            LeakCanaryAndroid        : \"com.squareup.leakcanary:leakcanary-android:2.0-alpha-1\",\n" +
                "            LeakCanary               : \"com.squareup.leakcanary:leaksentry:2.0-alpha-1\",\n" +
                "            KotlinJdk                : \"org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.50\",\n" +
                "    ]\n" +
                "\n" +
                "    KotlinCoroutinesConfiguration = [\n" +
                "            Coroutines       : \"org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3\",\n" +
                "            AndroidCoroutines: \"org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3\"\n" +
                "    ]\n" +
                "\n" +
                "    UiConfiguration = [\n" +
                "            RecyclerView      : \"androidx.recyclerview:recyclerview:1.0.0\",\n" +
                "            SupportCompat     : \"com.android.support:support-compat:1.0.0\",\n" +
                "            SupportCore       : \"com.android.support:support-core-utils:1.0.0\",\n" +
                "            SupportCoreUi     : \"com.android.support:support-core-ui:1.0.0\",\n" +
                "            SupportFragment   : \"com.android.support:support-fragment:1.0.0\",\n" +
                "            AndroidCore       : \"androidx.core:core-ktx:1.7.0\",\n" +
                "            CardView          : \"androidx.cardview:cardview:1.0.0\",\n" +
                "            AppCompat         : \"androidx.appcompat:appcompat:1.4.1\",\n" +
                "            GoogleMaterial    : \"com.google.android.material:material:1.6.0-alpha02\",\n" +
                "            Constraintlayout  : \"androidx.constraintlayout:constraintlayout:2.0.0-beta4\",\n" +
                "            NavigationFragment: \"androidx.navigation:navigation-fragment-ktx:2.4.0\",\n" +
                "            NavigationUI      : \"androidx.navigation:navigation-ui-ktx:2.4.0\",\n" +
                "            FragmentKtx: \"androidx.fragment:fragment-ktx:1.4.1\",\n" +
                "            Glide: \"com.github.bumptech.glide:glide:4.12.0\",\n" +
                "            GlideAnnotations: \"com.github.bumptech.glide:compiler:4.12.0\"\n" +
                "    ]\n" +
                "\n" +
                "    RxConfiguration = [\n" +
                "            RxJava   : \"io.reactivex.rxjava2:rxjava:2.2.11\",\n" +
                "            RxAndroid: \"io.reactivex.rxjava2:rxandroid:2.1.1\"\n" +
                "    ]\n" +
                "\n" +
                "    DataConfiguration = [\n" +
                "            Retrofit             : \"com.squareup.retrofit2:retrofit:2.9.0\",\n" +
                "            RetrofitRxJavaAdapter: \"com.squareup.retrofit2:adapter-rxjava2:2.9.0\",\n" +
                "            RetrofitIntercenptor : \"com.squareup.okhttp3:logging-interceptor:3.12.1\",\n" +
                "            retrofitGsonConverter: \"com.squareup.retrofit2:converter-gson:2.5.0\",\n" +
                "            Moshi                : \"com.squareup.moshi:moshi-kotlin:1.13.0\"\n" +
                "    ]\n" +
                "\n" +
                "    RoomConfiguration = [\n" +
                "            RoomRuntime : \"androidx.room:room-runtime:2.2.3\",\n" +
                "            RoomCompiler: \"androidx.room:room-compiler:2.2.3\",\n" +
                "            RoomKotlin  : \"androidx.room:room-ktx:2.2.3\",\n" +
                "            RoomRxJava  : \"androidx.room:room-rxjava2:2.2.3\"\n" +
                "    ]\n" +
                "\n" +
                "    LifeCycleConfiguration = [\n" +
                "            LifecycleViewModel  : \"androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0-alpha01\",\n" +
                "            LifecycleExtensions : \"androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-alpha01\",\n" +
                "            LifecycleRuntime    : \"androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-alpha01\",\n" +
                "            LifecycleLiveData   : \"androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-alpha01\",\n" +
                "            LifecycleCommon     : \"androidx.lifecycle:lifecycle-common:2.5.0-alpha01\",\n" +
                "            LifeCycleSavedState: \"androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.0-alpha01\"\n" +
                "    ]\n" +
                "\n" +
                "    GooglePlayServices = [\n" +
                "            GoogleMaps     : \"com.google.android.gms:play-services-maps:16.0.0\",\n" +
                "            GooglePlaces   : \"com.google.android.gms:play-services-places:16.0.0\",\n" +
                "            GoogleGcm      : \"com.google.android.gms:play-services-gcm:16.0.0\",\n" +
                "            GoogleLocations: \"com.google.android.gms:play-services-location:16.0.0\"\n" +
                "    ]\n" +
                "\n" +
                "    TestingConfiguration = [\n" +
                "            Esspresso: \"androidx.test.espresso:espresso-core:3.2.0\",\n" +
                "            Runner   : \"androidx.test:runner:1.2.0\",\n" +
                "            Junit    : \"junit:junit:4.12\"\n" +
                "    ]\n" +
                "\n" +
                "    DependenciesInjection = [\n" +
                "            Dagger: \"com.google.dagger:dagger:2.24\",\n" +
                "            DaggerCompiler : \"com.google.dagger:dagger-compiler:2.24\",\n" +
                "            DaggerAndroid : \"com.google.dagger:dagger-android:2.24\",\n" +
                "            DaggerAndroidSupport : \"com.google.dagger:dagger-android-support:2.24\",\n" +
                "            DaggerAndroidProcessor: \"com.google.dagger:dagger-android-processor:2.24\",\n" +
                "            Hilt: \"com.google.dagger:hilt-android:2.38.1\",\n" +
                "            HiltCompiler: \"com.google.dagger:hilt-compiler:2.38.1\"\n" +
                "    ]\n" +
                "\n" +
                "    VaniteConfiguration = [\n" +
                "            VaniteUserInterface : \"com.yazantarifi:Vanite-ui:1.0.0\",\n" +
                "            VaniteUserInterfaceBinding : \"com.yazantarifi:Vanite-ui-binding:1.0.0\",\n" +
                "            VanitePermissions : \"com.yazantarifi:Vanite-permissions:1.0.0\",\n" +
                "            VaniteCore : \"com.yazantarifi:Vanite:1.0.0\",\n" +
                "            VaniteData: \"com.yazantarifi:Vanite-data:1.0.0\",\n" +
                "            VaniteExtras: \"com.yazantarifi:Vanite-utils:1.0.0\",\n" +
                "            VanitePrefs: \"com.yazantarifi:Vanite-prefs:1.0.0\"\n" +
                "    ]\n" +
                "\n" +
                "    FirebaseConfiguration = [\n" +
                "            FirebaseCrashlytics: \"com.google.firebase:firebase-crashlytics-ktx\",\n" +
                "            FirebaseAnalytics: \"com.google.firebase:firebase-analytics-ktx\",\n" +
                "            FirebaseMessaging: \"com.google.firebase:firebase-messaging\",\n" +
                "            FirebaseFunctions: \"com.google.firebase:firebase-functions-ktx\",\n" +
                "            FirebasePlatform: \"com.google.firebase:firebase-bom:28.2.1\"\n" +
                "    ]\n" +
                "\n" +
                "    MoshiConfiguration = [\n" +
                "            MoshiRetrofitAdapter: \"com.squareup.retrofit2:converter-moshi:2.4.0\",\n" +
                "            RetrofitCorotinesAdapter: \"com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2\",\n" +
                "            MoshiCodegen: \"com.squareup.moshi:moshi-kotlin-codegen:1.12.0\",\n" +
                "            JvmMetaData: \"org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.3.0\"\n" +
                "    ]\n" +
                "\n" +
                "    RoomConfiguration = [\n" +
                "            RoomRunTime: \"androidx.room:room-runtime:2.4.2\",\n" +
                "            RoomCompiler: \"androidx.room:room-compiler:2.4.2\",\n" +
                "            RoomPaging: \"androidx.room:room-paging:2.4.2\"\n" +
                "    ]\n" +
                "\n" +
                "    GuavaConfiguration = [\n" +
                "            Guava: \"com.google.guava:guava:31.0.1-jre\",\n" +
                "            GuavaApi: \"com.google.guava:guava:31.0.1-jre\",\n" +
                "            GuavaAndroid: \"com.google.guava:guava:31.0.1-android\",\n" +
                "            GuavaAndroidApi: \"com.google.guava:guava:31.0.1-android\"\n" +
                "    ]\n" +
                "\n" +
                "    WorkManagerConfiguration = [\n" +
                "            WorkManager: \"androidx.work:work-runtime:2.7.1\",\n" +
                "            WorkManagerRunTime: \"androidx.work:work-runtime-ktx:2.7.1\",\n" +
                "            Gcm: \"androidx.work:work-gcm:2.7.1\"\n" +
                "    ]\n" +
                "\n" +
                "}"
    }

    override fun getFileExt(): FileExtention = FileExtention.GRADLE
    override fun getFilePath(): String = generatedFilePath

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }
}