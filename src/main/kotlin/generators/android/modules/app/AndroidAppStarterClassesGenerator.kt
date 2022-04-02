package generators.android.modules.app

import generators.base.MultiFileGenerator
import models.FileExtention
import java.io.File

class AndroidAppStarterClassesGenerator constructor(
    private val generatedPathBase: String,
    private val packageName: String,
    private val onGeneratedFileListener: (String) -> Unit
): MultiFileGenerator() {

    override fun execute() {
        File("$generatedPathBase/screens").mkdir()
        File("$generatedPathBase/utils").mkdir()

        generateFile("SplashScreen", getSplashScreen(), FileExtention.KOTLIN, "$generatedPathBase/screens", onGeneratedFileListener)
        generateFile("ApplicationLinkScreen", getAppLinkScreen(), FileExtention.KOTLIN, "$generatedPathBase/screens", onGeneratedFileListener)
        generateFile("TimberCrashReportingTree", getTimberTree(), FileExtention.KOTLIN, "$generatedPathBase/utils", onGeneratedFileListener)
        generateFile("App", getApp(), FileExtention.KOTLIN, generatedPathBase, onGeneratedFileListener)
    }

    private fun getSplashScreen(): String {
        return "package ${packageName}.screens\n" +
                "\n" +
                "import android.os.Bundle\n" +
                "import androidx.appcompat.app.AppCompatActivity\n" +
                "import ${packageName}.R\n" +
                "\n" +
                "class SplashScreen: AppCompatActivity() {\n" +
                "\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        setContentView(R.layout.activity_main)\n" +
                "    }\n" +
                "\n" +
                "}"
    }

    private fun getAppLinkScreen(): String {
        return "package ${packageName}.screens\n" +
                "\n" +
                "import android.os.Bundle\n" +
                "import androidx.appcompat.app.AppCompatActivity\n" +
                "import ${packageName}.R\n" +
                "\n" +
                "class ApplicationLinkScreen: AppCompatActivity() {\n" +
                "\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        setContentView(R.layout.activity_main)\n" +
                "    }\n" +
                "\n" +
                "}\n"
    }

    private fun getTimberTree(): String {
        return "package ${packageName}.utils\n" +
                "\n" +
                "\n" +
                "import android.R.attr\n" +
                "import android.util.Log\n" +
                "import com.google.firebase.crashlytics.FirebaseCrashlytics\n" +
                "import timber.log.Timber\n" +
                "\n" +
                "\n" +
                "class TimberCrashReportingTree: Timber.Tree() {\n" +
                "\n" +
                "    companion object {\n" +
                "        private const val CRASHLYTICS_KEY_MESSAGE = \"message\"\n" +
                "        private const val CRASHLYTICS_KEY_TAG = \"tag\"\n" +
                "        private const val CRASHLYTICS_KEY_PRIORITY = \"priority\"\n" +
                "    }\n" +
                "\n" +
                "    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {\n" +
                "        if (attr.priority == Log.VERBOSE || attr.priority == Log.DEBUG) {\n" +
                "            return\n" +
                "        }\n" +
                "\n" +
                "        val t: Throwable = t ?: Exception(message)\n" +
                "        try {\n" +
                "            FirebaseCrashlytics.getInstance().setCustomKey(CRASHLYTICS_KEY_PRIORITY, attr.priority)\n" +
                "            tag?.let { FirebaseCrashlytics.getInstance().setCustomKey(CRASHLYTICS_KEY_TAG, it) }\n" +
                "            FirebaseCrashlytics.getInstance().setCustomKey(CRASHLYTICS_KEY_MESSAGE, message)\n" +
                "        } catch (ex: Exception) {\n" +
                "            FirebaseCrashlytics.getInstance().recordException(ex)\n" +
                "        }\n" +
                "\n" +
                "        FirebaseCrashlytics.getInstance().recordException(t)\n" +
                "    }\n" +
                "\n" +
                "}\n"
    }

    private fun getApp(): String {
        return "package $packageName\n" +
                "\n" +
                "import androidx.multidex.MultiDexApplication\n" +
                "import com.google.firebase.FirebaseApp\n" +
                "import com.google.firebase.analytics.FirebaseAnalytics\n" +
                "import com.google.firebase.crashlytics.FirebaseCrashlytics\n" +
                "import com.google.firebase.messaging.FirebaseMessaging\n" +
                "import $packageName.utils.TimberCrashReportingTree\n" +
                "import dagger.hilt.android.HiltAndroidApp\n" +
                "import timber.log.Timber\n" +
                "\n" +
                "@HiltAndroidApp\n" +
                "class App: MultiDexApplication(), Thread.UncaughtExceptionHandler {\n" +
                "\n" +
                "    override fun onCreate() {\n" +
                "        super.onCreate()\n" +
                "        registerTimberConfiguration()\n" +
                "        try {\n" +
                "            FirebaseApp.initializeApp(this)\n" +
                "            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)\n" +
                "            FirebaseCrashlytics.getInstance().sendUnsentReports()\n" +
                "            FirebaseMessaging.getInstance().isAutoInitEnabled = true\n" +
                "            FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)\n" +
                "        } catch (ex: Exception) {\n" +
                "            Timber.e(ex)\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    override fun uncaughtException(p0: Thread, p1: Throwable) {\n" +
                "        Timber.e(p1)\n" +
                "    }\n" +
                "\n" +
                "    private fun registerTimberConfiguration() {\n" +
                "        if (BuildConfig.DEBUG) {\n" +
                "            Timber.plant(Timber.DebugTree())\n" +
                "        } else {\n" +
                "            Timber.plant(TimberCrashReportingTree())\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}"
    }

}
