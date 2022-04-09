package generators.base

import utils.FileUtils
import java.io.File

abstract class TemplatingFilesGenerator: MultiFileGenerator() {

    override fun execute() = Unit

    protected fun generateDirectory(path: String, onMessageGenerated: (String) -> Unit) {
        if (File(path).mkdir()) {
            onMessageGenerated("Folder Generated Successfully With Path : $path")
        } else {
            onMessageGenerated("Folder Generated Failed With Path : $path")
        }
    }

    protected fun getFileContent(name: String): String {
        try {
            var results = ""
            val inputStream = FileUtils.getFileFromResourceAsStream(name)
            inputStream?.let {
                FileUtils.printInputStream(it) {
                    results += it + "\n"
                }
            }
            return results
        } catch (ex: Exception) {
            println("Failed, File Not Found : ${ex.message}")
            ex.printStackTrace()
            return ""
        }
    }

}
