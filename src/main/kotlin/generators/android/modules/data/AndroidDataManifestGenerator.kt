package generators.android.modules.data

import generators.base.FileGenerator
import models.FileExtention

class AndroidDataManifestGenerator(
    private val packageName: String,
    private val generatedFilePath: String,
    private val onMessageGenerated: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String = "AndroidManifest"
    override fun getFileExt(): FileExtention = FileExtention.XML
    override fun getFilePath(): String = "$generatedFilePath/data/src/main"
    override fun getFileContent(): String {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "    package=\"${"$packageName.data"}\">\n" +
                "\n" +
                "</manifest>"
    }

    override fun execute() {
        generateFile {
            onMessageGenerated(it)
        }
    }

}
