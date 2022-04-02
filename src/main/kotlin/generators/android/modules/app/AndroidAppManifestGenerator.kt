package generators.android.modules.app

import generators.base.FileGenerator
import models.FileExtention

class AndroidAppManifestGenerator constructor(
    private val packageName: String,
    private val projectName: String,
    private val generatedFilePath: String,
    private val onMessageGenerated: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String = "AndroidManifest"
    override fun getFileExt(): FileExtention = FileExtention.XML
    override fun getFilePath(): String = "$generatedFilePath/app/src/main"
    override fun getFileContent(): String {
       return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
               "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
               "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
               "    package=\"${packageName}\">\n" +
               "    <uses-permission android:name=\"android.permission.ACCESS_WIFI_STATE\" />\n" +
               "    <uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />\n" +
               "    <uses-permission android:name=\"android.permission.INTERNET\" />\n" +
               "\n" +
               "    <application\n" +
               "        android:allowBackup=\"false\"\n" +
               "        android:icon=\"@mipmap/ic_launcher\"\n" +
               "        android:label=\"@string/app_name\"\n" +
               "        android:hardwareAccelerated=\"true\"\n" +
               "        android:largeHeap=\"true\"\n" +
               "        android:name=\".${projectName.replace(" ", "")}Application\"\n" +
               "        android:roundIcon=\"@mipmap/ic_launcher\"\n" +
               "        android:supportsRtl=\"true\"\n" +
               "        android:theme=\"@style/Theme.LeagueClient\"\n" +
               "        tools:targetApi=\"31\"\n" +
               "        tools:ignore=\"UnusedAttribute\">\n" +
               "\n" +
               "        <activity\n" +
               "            android:name=\".screens.SplashScreen\"\n" +
               "            android:exported=\"true\"\n" +
               "            android:screenOrientation=\"portrait\"\n" +
               "            android:theme=\"@style/SplashScreenTheme\">\n" +
               "            <intent-filter>\n" +
               "                <action android:name=\"android.intent.action.MAIN\" />\n" +
               "\n" +
               "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" +
               "            </intent-filter>\n" +
               "        </activity>\n" +
               "\n" +
               "        <activity\n" +
               "            android:name=\".screens.ApplicationLinkScreen\"\n" +
               "            android:exported=\"true\"\n" +
               "            android:launchMode=\"singleTop\"\n" +
               "            android:configChanges=\"locale|orientation\"\n" +
               "            android:theme=\"@style/Theme.LeagueClient.NoActionBar\"\n" +
               "            android:screenOrientation=\"portrait\">\n" +
               "            <intent-filter android:autoVerify=\"true\">\n" +
               "                <action android:name=\"android.intent.action.VIEW\"/>\n" +
               "                <category android:name=\"android.intent.category.DEFAULT\"/>\n" +
               "                <category android:name=\"android.intent.category.BROWSABLE\"/>\n" +
               "\n" +
               "                <data android:host=\"www.legora.com\" android:pathPrefix=\"/champion/id/\" android:scheme=\"https\"/>\n" +
               "                <data android:host=\"www.legora.com\" android:pathPrefix=\"/champion/id/\" android:scheme=\"http\"/>\n" +
               "\n" +
               "                <data android:host=\"www.legora.com\" android:pathPrefix=\"/champions\" android:scheme=\"https\"/>\n" +
               "                <data android:host=\"www.legora.com\" android:pathPrefix=\"/champions\" android:scheme=\"http\"/>\n" +
               "\n" +
               "                <data android:host=\"www.legora.com\" android:pathPrefix=\"/summoner/id/\" android:scheme=\"https\"/>\n" +
               "                <data android:host=\"www.legora.com\" android:pathPrefix=\"/summoner/id/\" android:scheme=\"http\"/>\n" +
               "\n" +
               "            </intent-filter>\n" +
               "        </activity>\n" +
               "\n" +
               "        <meta-data\n" +
               "            android:name=\"com.google.firebase.messaging.default_notification_icon\"\n" +
               "            android:resource=\"@drawable/icon_red\" />\n" +
               "\n" +
               "        <meta-data\n" +
               "            android:name=\"com.google.firebase.messaging.default_notification_color\"\n" +
               "            android:resource=\"@color/purple_200\" />\n" +
               "\n" +
               "        <meta-data\n" +
               "            android:name=\"firebase_messaging_auto_init_enabled\"\n" +
               "            android:value=\"true\" />\n" +
               "\n" +
               "        <meta-data\n" +
               "            android:name=\"firebase_analytics_collection_enabled\"\n" +
               "            android:value=\"true\" />\n" +
               "\n" +
               "    </application>\n" +
               "\n" +
               "</manifest>"
    }

    override fun execute() {
        generateFile {
            onMessageGenerated(it)
        }
    }

}