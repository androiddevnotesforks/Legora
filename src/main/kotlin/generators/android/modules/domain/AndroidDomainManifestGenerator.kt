package generators.android.modules.domain

import generators.base.FileGenerator
import models.FileExtention

class AndroidDomainManifestGenerator(
    private val packageName: String,
    private val generatedFilePath: String,
    private val onMessageGenerated: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String = "AndroidManifest"
    override fun getFileExt(): FileExtention = FileExtention.XML
    override fun getFilePath(): String = "$generatedFilePath/domain/src/main"
    override fun getFileContent(): String {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "    package=\"${"$packageName.domain"}\">\n" +
                "\n" +
                "</manifest>"
    }

    override fun execute() {
        generateFile {
            onMessageGenerated(it)
        }
    }
}