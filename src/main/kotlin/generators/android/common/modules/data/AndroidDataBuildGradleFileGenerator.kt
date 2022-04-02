package generators.android.common.modules.data

import generators.base.FileGenerator
import models.FileExtention

class AndroidDataBuildGradleFileGenerator constructor(
    private val generatedFilePath: String,
    private val onMessageGenerated: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String {
        return "build"
    }

    override fun getFileContent(): String {
        return "\n" +
                "dependencies { configuration ->\n" +
                "    implementation fileTree(dir: 'libs', include: ['*.jar'])\n" +
                "    rxJavaConfiguration(configuration)\n" +
                "    retrofitConfiguration(configuration)\n" +
                "    lifeCycleConfiguration(configuration)\n" +
                "    roomConfiguration(configuration)\n" +
                "    moshiConfiguration(configuration)\n" +
                "}\n" +
                "\n" +
                "private void roomConfiguration(configuration) {\n" +
                "    def RoomConfiguration = rootProject.ext.RoomConfiguration\n" +
                "    configuration.implementation RoomConfiguration.RoomRunTime\n" +
                "    configuration.implementation RoomConfiguration.RoomPaging\n" +
                "    configuration.kapt RoomConfiguration.RoomCompiler\n" +
                "}\n" +
                "\n" +
                "private void moshiConfiguration(configuration) {\n" +
                "    def MoshiConfiguration = rootProject.ext.MoshiConfiguration\n" +
                "    configuration.implementation MoshiConfiguration.MoshiRetrofitAdapter\n" +
                "    configuration.implementation MoshiConfiguration.RetrofitCorotinesAdapter\n" +
                "    configuration.kapt MoshiConfiguration.JvmMetaData\n" +
                "    configuration.kapt MoshiConfiguration.MoshiCodegen\n" +
                "}\n" +
                "\n" +
                "private void rxJavaConfiguration(configuration) {\n" +
                "    def RxConfiguration = rootProject.ext.RxConfiguration\n" +
                "    configuration.implementation RxConfiguration.RxJava\n" +
                "    configuration.implementation RxConfiguration.RxAndroid\n" +
                "}\n" +
                "\n" +
                "private void retrofitConfiguration(configuration) {\n" +
                "    def DataConfiguration = rootProject.ext.DataConfiguration\n" +
                "    configuration.implementation DataConfiguration.Retrofit\n" +
                "    configuration.implementation DataConfiguration.RetrofitRxJavaAdapter\n" +
                "    configuration.implementation DataConfiguration.RetrofitIntercenptor\n" +
                "    configuration.implementation DataConfiguration.retrofitGsonConverter\n" +
                "    configuration.implementation DataConfiguration.Moshi\n" +
                "}"
    }

    override fun getFileExt(): FileExtention = FileExtention.GRADLE
    override fun getFilePath(): String = generatedFilePath

    override fun execute() {
        generateFile {
            onMessageGenerated(it)
        }
    }

}