package generators.android.modules

import generators.android.modules.app.AndroidAppStarterClassesGenerator
import generators.base.DirectoryGenerator

class AndroidPackageNameGenerator constructor(
    private val generatedPath: String,
    private val packageName: String,
    private val isSingleModuleApplication: Boolean,
    private val onGeneratedFileListener: (String) -> Unit
): DirectoryGenerator() {

    override fun execute() {
        if (isSingleModuleApplication) {
            generateFileInternal("app")
        } else {
            generateFileInternal("app")
            generateFileInternal("data")
            generateFileInternal("domain")
        }
    }

    private fun generateFileInternal(path: String) {
        var generatedPathBase = "$generatedPath/$path/src/main/java"
        packageName.split(".").forEach {
            generatedPathBase += "/$it"
            generateDirectory(
                generatedPathBase,
                onGeneratedFileListener
            )
        }

        if (!path.equals("app")) {
            generatedPathBase += "/$path"
            generateDirectory(generatedPathBase, onGeneratedFileListener)
        } else {
            AndroidAppStarterClassesGenerator(generatedPathBase, packageName, onGeneratedFileListener).execute()
        }
    }

}
