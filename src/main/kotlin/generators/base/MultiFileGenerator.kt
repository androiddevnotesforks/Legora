package generators.base

import models.FileExtention
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

abstract class MultiFileGenerator {

    protected fun generateFile(fileName: String, content: String, fileExtention: FileExtention, path: String, onMessageGenerated: (String) -> Unit) {
        val fileName = fileName + fileExtention.key
        try {
            val file: File = File("$path/$fileName")
            if (!file.exists()) {
                file.createNewFile()
            }

            val fileWriter = FileWriter(file.absoluteFile)
            val bufferReader = BufferedWriter(fileWriter)
            bufferReader.write(content)
            bufferReader.close()
            onMessageGenerated("$fileName Generated Successfully With Path ${file.absoluteFile}")
        } catch (ex: Exception) {
            onMessageGenerated("$fileName Generated Failed With Exception : ${ex.message}")
        }
    }

    abstract fun execute()

}
