package generators.base

import models.FileExtention
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


abstract class FileGenerator {

    protected fun generateFile(onMessageGenerated: (String) -> Unit) {
        val fileName = getFileName() + getFileExt().key
        try {
            val file: File = File(getFilePath() + fileName)
            if (!file.exists()) {
                file.createNewFile()
            }

            val fileWriter = FileWriter(file.absoluteFile)
            val bufferReader = BufferedWriter(fileWriter)
            bufferReader.write(getFileContent())
            bufferReader.close()
            onMessageGenerated("$fileName Generated Successfully With Path ${file.absoluteFile}")
        } catch (ex: Exception) {
            onMessageGenerated("$fileName Generated Failed With Exception : ${ex.message}")
        }
    }

    abstract fun getFileName(): String

    abstract fun getFileContent(): String

    abstract fun getFileExt(): FileExtention

    abstract fun getFilePath(): String

    abstract fun execute()

}
