package generators.android.common.gradle

import generators.base.FileGenerator
import models.FileExtention

class AndroidGradleWrapperProperitiesGenerator constructor(
    private val pathGeneratedFile: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String {
        return "gradle-wrapper"
    }

    override fun getFileContent(): String {
        return "#Fri Apr 01 14:31:16 EEST 2022\n" +
                "distributionBase=GRADLE_USER_HOME\n" +
                "distributionUrl=https\\://services.gradle.org/distributions/gradle-7.0.2-bin.zip\n" +
                "distributionPath=wrapper/dists\n" +
                "zipStorePath=wrapper/dists\n" +
                "zipStoreBase=GRADLE_USER_HOME\n"
    }

    override fun getFileExt(): FileExtention = FileExtention.PROPERTIES
    override fun getFilePath(): String = pathGeneratedFile

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }
}