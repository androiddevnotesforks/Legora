package generators.android.modules.app

import generators.base.MultiFileGenerator
import models.FileExtention

class AndroidAppResourcesFilesGenerator constructor(
    private val generatedFilePath: String,
    private val onMessageGenerated: (String) -> Unit
): MultiFileGenerator() {

    override fun execute() {
        val path = "$generatedFilePath/app/src/main/res/"
        generateFile("colors", getColorsFile(), FileExtention.XML, "$path/values", onMessageGenerated)
        generateFile("colors", getColorsFile(), FileExtention.XML, "$path/values-night", onMessageGenerated)

        generateFile("themes", getThemes(), FileExtention.XML, "$path/values", onMessageGenerated)
        generateFile("themes", getThemes(), FileExtention.XML, "$path/values-night", onMessageGenerated)

        generateFile("strings", getStringsFile(), FileExtention.XML, "$path/values", onMessageGenerated)
        generateFile("strings", getStringsFile(), FileExtention.XML, "$path/values-ar", onMessageGenerated)

        generateFile("activity_main", getLayout(), FileExtention.XML, "$path/layout", onMessageGenerated)
        generateFile("splash_screen_background", getSplashScreenDrawable(), FileExtention.XML, "$path/drawable", onMessageGenerated)
    }

    private fun getColorsFile(): String {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "    <color name=\"purple_200\">#FF5252</color>\n" +
                "    <color name=\"purple_500\">#FF5252</color>\n" +
                "    <color name=\"purple_700\">#FF5252</color>\n" +
                "    <color name=\"teal_200\">#FF5252</color>\n" +
                "    <color name=\"teal_700\">#FF5252</color>\n" +
                "    <color name=\"black\">#FF000000</color>\n" +
                "    <color name=\"white\">#FFFFFFFF</color>\n" +
                "    <color name=\"title\">#000000</color>\n" +
                "    <color name=\"gray\">#888888</color>\n" +
                "    <color name=\"gray_2\">#DCDCDC</color>\n" +
                "    <color name=\"back\">#FFFFFF</color>\n" +
                "    <color name=\"green\">#4CAF50</color>\n" +
                "</resources>"
    }

    private fun getStringsFile(): String {
        return "<resources>\n" +
                "    <string name=\"app_name\">Legora</string>\n" +
                "</resources>"
    }

    private fun getThemes(): String {
        return "<resources xmlns:tools=\"http://schemas.android.com/tools\">\n" +
                "    <!-- Base application theme. -->\n" +
                "    <style name=\"Theme.LeagueClient\" parent=\"Theme.MaterialComponents.DayNight.DarkActionBar\">\n" +
                "        <item name=\"colorPrimary\">@color/purple_500</item>\n" +
                "        <item name=\"colorPrimaryVariant\">@color/purple_700</item>\n" +
                "        <item name=\"colorOnPrimary\">@color/white</item>\n" +
                "        <item name=\"colorSecondary\">@color/teal_200</item>\n" +
                "        <item name=\"colorSecondaryVariant\">@color/teal_700</item>\n" +
                "        <item name=\"colorOnSecondary\">@color/black</item>\n" +
                "        <item name=\"android:statusBarColor\" tools:targetApi=\"l\">?attr/colorPrimaryVariant</item>\n" +
                "    </style>\n" +
                "\n" +
                "    <style name=\"Theme.LeagueClient.NoActionBar\">\n" +
                "        <item name=\"windowActionBar\">false</item>\n" +
                "        <item name=\"windowNoTitle\">true</item>\n" +
                "    </style>\n" +
                "\n" +
                "    <style name=\"Theme.LeagueClient.AppBarOverlay\" parent=\"ThemeOverlay.AppCompat.Dark.ActionBar\" />\n" +
                "\n" +
                "    <style name=\"Theme.LeagueClient.PopupOverlay\" parent=\"ThemeOverlay.AppCompat.Light\" />\n" +
                "\n" +
                "    <style name=\"SplashScreenTheme\" parent=\"Theme.MaterialComponents.Light.NoActionBar.Bridge\">\n" +
                "        <item name=\"windowNoTitle\">true</item>\n" +
                "        <item name=\"windowActionBar\">false</item>\n" +
                "        <item name=\"android:windowBackground\">@drawable/splash_screen_background</item>\n" +
                "        <item name=\"colorPrimary\">@color/purple_200</item>\n" +
                "        <item name=\"colorPrimaryDark\">@color/purple_200</item>\n" +
                "        <item name=\"colorAccent\">@color/purple_200</item>\n" +
                "    </style>\n" +
                "\n" +
                "</resources>"
    }

    private fun getLayout(): String {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<androidx.constraintlayout.widget.ConstraintLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "    xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n" +
                "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
                "    android:layout_width=\"match_parent\"\n" +
                "    android:layout_height=\"match_parent\"/>"
    }

    private fun getSplashScreenDrawable(): String {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<layer-list xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                "\n" +
                "    <item android:drawable=\"@color/purple_200\" />\n" +
                "\n" +
                "<!--    <item android:drawable=\"@drawable/icon_white\"-->\n" +
                "<!--        android:width=\"90dp\"-->\n" +
                "<!--        android:height=\"90dp\"-->\n" +
                "<!--        android:gravity=\"center\" />-->\n" +
                "\n" +
                "</layer-list>"
    }

}
