package utils

import java.io.*
import java.net.URISyntaxException
import java.net.URL
import java.nio.charset.StandardCharsets


object FileUtils {

    fun getFileFromResourceAsStream(fileName: String): InputStream? {
        val classLoader = javaClass.classLoader
        val inputStream = classLoader.getResourceAsStream(fileName)

        return inputStream ?: throw IllegalArgumentException("file not found! $fileName")
    }

    @Throws(URISyntaxException::class)
    fun getFileFromResource(fileName: String): File? {
        val classLoader = javaClass.classLoader
        val resource: URL? = classLoader.getResource(fileName)
        return if (resource == null) {
            throw IllegalArgumentException("file not found! $fileName")
        } else {
            File(resource.toURI())
        }
    }

    fun printInputStream(`is`: InputStream, onLineRead: (String) -> Unit) {
        try {
            InputStreamReader(`is`, StandardCharsets.UTF_8).use { streamReader ->
                BufferedReader(streamReader).use { reader ->
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        onLineRead(line ?: "")
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}