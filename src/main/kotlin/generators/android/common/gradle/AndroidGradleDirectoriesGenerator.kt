package generators.android.common.gradle

import generators.base.DirectoryGenerator

class AndroidGradleDirectoriesGenerator constructor(
    private val generatedPath: String,
    private val onMessageGenerated: (String) -> Unit
): DirectoryGenerator() {
    override fun execute() {
        generateDirectories(arrayListOf(
            generatedPath + "gradle",
            generatedPath + "gradle" + "/" + "wrapper"
        ), onMessageGenerated)
    }
}
