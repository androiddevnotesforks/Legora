package generators.android.modules

import generators.base.DirectoryGenerator

class AndroidApplicationModulesDirectoriesGenerator constructor(
    private val generatedPath: String,
    private val isSingleModuleApplication: Boolean,
    private val onMessageGenerated: (String) -> Unit
): DirectoryGenerator() {
    override fun execute() {
        if (isSingleModuleApplication) {
            generateDirectory("$generatedPath/app", onMessageGenerated)
        } else {
            generateDirectories(arrayListOf(
                "$generatedPath/app",
                "$generatedPath/domain",
                "$generatedPath/data",
            ), onMessageGenerated)
        }
    }
}