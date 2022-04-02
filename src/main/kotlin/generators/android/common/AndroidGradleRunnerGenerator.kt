package generators.android.common

import generators.base.FileGenerator
import models.FileExtention
import utils.FileUtils
import java.io.File

class AndroidGradleRunnerGenerator constructor(
    private val generatedFilePath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String = "gradlew"
    override fun getFileExt(): FileExtention = FileExtention.EMPTY
    override fun getFilePath(): String = generatedFilePath

    override fun getFileContent(): String {
        var results = ""
        val inputStream = FileUtils.getFileFromResourceAsStream("gradle-runner-android.txt")
        inputStream?.let {
            FileUtils.printInputStream(it) {
                results += it + "\n"
            }
        }
        return results
    }

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }


}