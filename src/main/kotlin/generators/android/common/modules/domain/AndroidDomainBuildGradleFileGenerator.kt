package generators.android.common.modules.domain

import generators.base.FileGenerator
import models.FileExtention

class AndroidDomainBuildGradleFileGenerator constructor(
    private val generatedFilePath: String,
    private val onMessageGenerated: (String) -> Unit
): FileGenerator() {
    override fun getFileName(): String {
        return "build"
    }

    override fun getFileContent(): String {
        return "\n" +
                "dependencies { configuration ->\n" +
                "    implementation project(\":data\")\n" +
                "    daggerConfig(configuration)\n" +
                "    lifeCycleConfiguration(configuration)\n" +
                "    retrofitConfiguration(configuration)\n" +
                "}\n" +
                "\n" +
                "private void retrofitConfiguration(configuration) {\n" +
                "    def DataConfiguration = rootProject.ext.DataConfiguration\n" +
                "    configuration.implementation DataConfiguration.Retrofit\n" +
                "    configuration.implementation DataConfiguration.RetrofitRxJavaAdapter\n" +
                "    configuration.implementation DataConfiguration.RetrofitIntercenptor\n" +
                "    configuration.implementation DataConfiguration.retrofitGsonConverter\n" +
                "    configuration.implementation DataConfiguration.Moshi\n" +
                "}\n"
    }

    override fun getFileExt(): FileExtention = FileExtention.GRADLE
    override fun getFilePath(): String = generatedFilePath

    override fun execute() {
        generateFile {
            onMessageGenerated(it)
        }
    }
}