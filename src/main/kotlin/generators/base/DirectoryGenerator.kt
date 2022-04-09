package generators.base

import java.io.File
import java.util.concurrent.TimeUnit

abstract class DirectoryGenerator {

    protected fun generateDirectory(path: String, onMessageGenerated: (String) -> Unit) {
        TimeUnit.MILLISECONDS.sleep(500)
        if (File(path).mkdir()) {
            onMessageGenerated("Folder Generated Successfully With Path : $path")
        } else {
            onMessageGenerated("Folder Generated Failed With Path : $path")
        }
    }

    protected fun generateDirectories(items: ArrayList<String>, onMessageGenerated: (String) -> Unit) {
        items.forEach {
            generateDirectory(it, onMessageGenerated)
        }
    }

    abstract fun execute()

}
