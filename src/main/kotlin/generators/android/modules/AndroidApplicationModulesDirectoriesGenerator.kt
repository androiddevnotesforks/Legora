package generators.android.modules

import generators.base.DirectoryGenerator

class AndroidApplicationModulesDirectoriesGenerator constructor(
    private val generatedPath: String,
    private val isSingleModuleApplication: Boolean,
    private val onMessageGenerated: (String) -> Unit
): DirectoryGenerator() {

    override fun execute() {
        if (isSingleModuleApplication) {
            generateDirectories(arrayListOf(
                "$generatedPath/app",
                "$generatedPath/app/src",
                "$generatedPath/app/test",
                "$generatedPath/app/androidTest",
                "$generatedPath/app/src/main",
                "$generatedPath/app/src/main/java",
                "$generatedPath/app/src/main/res",
                ), onMessageGenerated)
        } else {
            generateDirectories(arrayListOf(
                "$generatedPath/app",
                "$generatedPath/domain",
                "$generatedPath/data",

                "$generatedPath/app/src",
                "$generatedPath/domain/src",
                "$generatedPath/data/src",

                "$generatedPath/app/test",
                "$generatedPath/domain/test",
                "$generatedPath/data/test",

                "$generatedPath/app/androidTest",
                "$generatedPath/domain/androidTest",
                "$generatedPath/data/androidTest",

                "$generatedPath/app/src/main",
                "$generatedPath/domain/src/main",
                "$generatedPath/data/src/main",

                "$generatedPath/app/src/main/java",
                "$generatedPath/domain/src/main/java",
                "$generatedPath/data/src/main/java",

                "$generatedPath/app/src/main/res",
                "$generatedPath/domain/src/main/res",
                "$generatedPath/data/src/main/res",
            ), onMessageGenerated)
        }
    }

}
