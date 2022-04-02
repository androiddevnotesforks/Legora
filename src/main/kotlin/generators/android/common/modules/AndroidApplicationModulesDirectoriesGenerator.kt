package generators.android.common.modules

import generators.base.DirectoryGenerator

class AndroidApplicationModulesDirectoriesGenerator constructor(
    private val generatedPath: String,
    private val isSingleModuleApplication: Boolean,
    private val onMessageGenerated: (String) -> Unit
): DirectoryGenerator() {
    override fun execute() {
        if (isSingleModuleApplication) {
            generateDirectory("${generatedPath.dropLast(1)}/app", onMessageGenerated)
        } else {
            generateDirectories(arrayListOf(
                "${generatedPath.dropLast(1)}/app",
                "${generatedPath.dropLast(1)}/domain",
                "${generatedPath.dropLast(1)}/data",
            ), onMessageGenerated)
        }
    }
}