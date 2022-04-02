package generators.android.modules

import generators.base.DirectoryGenerator

class AndroidResourcesDirectoriesGenerator constructor(
    private val generatedPath: String,
    private val isSingleModuleApplication: Boolean,
    private val onGeneratedFileListener: (String) -> Unit
): DirectoryGenerator() {

    private val files = arrayListOf(
        "drawable",
        "drawable-v24",
        "drawable-v21",
        "drawable-anydpi",
        "drawable-hdpi",
        "drawable-mdpi",
        "drawable-xhdpi",
        "drawable-xxhdpi",
        "drawable-xxxhdpi",
        "mipmap-anydpi",
        "mipmap-hdpi",
        "mipmap-mdpi",
        "mipmap-xhdpi",
        "mipmap-xxhdpi",
        "mipmap-xxxhdpi",
        "layout",
        "values",
        "values-ar",
        "values-night",
    )

    override fun execute() {
        files.forEach {
            generateInternalFile("app", it)
        }

        if (!isSingleModuleApplication) {
            files.forEach {
                generateInternalFile("data", it)
                generateInternalFile("domain", it)
            }
        }
    }

    private fun generateInternalFile(module: String, fileName: String) {
        val path = "$generatedPath/$module/src/main/res/$fileName"
        generateDirectory(path, onGeneratedFileListener)
    }

}
