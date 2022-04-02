package generators.android.common.modules

import generators.base.FileGenerator
import models.FileExtention

class GoogleServicesFileGenerator constructor(
    private val generatedPath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String {
        return "google-services"
    }

    override fun getFileContent(): String {
        return "{\n" +
                "  \"project_info\": {\n" +
                "    \"project_number\": \"529627\",\n" +
                "    \"project_id\": \"leagu2af\",\n" +
                "    \"storage_bucket\": \"leagut.com\"\n" +
                "  },\n" +
                "  \"client\": [\n" +
                "    {\n" +
                "      \"client_info\": {\n" +
                "        \"mobilesdk_app_id\": \"1:a85e\",\n" +
                "        \"android_client_info\": {\n" +
                "          \"package_name\": \"com.yazantarifi.legora\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"oauth_client\": [\n" +
                "        {\n" +
                "          \"client_id\": \"5235ogleusercontent.com\",\n" +
                "          \"client_type\": 3\n" +
                "        }\n" +
                "      ],\n" +
                "      \"api_key\": [\n" +
                "        {\n" +
                "          \"current_key\": \"AIzaSao\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"services\": {\n" +
                "        \"appinvite_service\": {\n" +
                "          \"other_platform_oauth_client\": [\n" +
                "            {\n" +
                "              \"client_id\": \"523ercontent.com\",\n" +
                "              \"client_type\": 3\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"configuration_version\": \"1\"\n" +
                "}"
    }

    override fun getFileExt(): FileExtention = FileExtention.JSON
    override fun getFilePath(): String = generatedPath

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }
}