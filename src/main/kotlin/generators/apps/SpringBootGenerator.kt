package generators.apps

import generators.base.ProjectGeneratorImplementation
import generators.base.TemplatingFilesGenerator
import models.ApplicationDependency
import models.FileExtention
import models.ProjectInformationItem

class SpringBootGenerator constructor(
    private val isJavaLanguage: Boolean,
    private val generatedPath: String,
    private val onGeneratedFileListener: (String) -> Unit
): TemplatingFilesGenerator(), ProjectGeneratorImplementation {

    override fun generateProject(dependencies: ArrayList<ApplicationDependency>, fields: HashMap<String, String>) {
        val name = fields[ProjectInformationItem.NAME] ?: ""
        val group = fields[ProjectInformationItem.GROUP] ?: ""
        val version = fields[ProjectInformationItem.VERSION] ?: ""
        val artifact = fields[ProjectInformationItem.ARTIFACE] ?: ""
        val description = fields[ProjectInformationItem.DESCRIPTION] ?: ""
        generateDirectories("$group.$artifact")
        generateRootFiles(group, description, version, artifact, name)
    }

    private fun generateDirectories(packageName: String) {
        generateDirectory("$generatedPath/src", onGeneratedFileListener)
        generateDirectory("$generatedPath/src/main", onGeneratedFileListener)
        generateDirectory("$generatedPath/src/main/kotlin", onGeneratedFileListener)
        generatePackageNameDirectories(packageName)

        generateDirectory("$generatedPath/src/resources", onGeneratedFileListener)
        generateDirectory("$generatedPath/.mvn", onGeneratedFileListener)
        generateDirectory("$generatedPath/.mvn/wrapper", onGeneratedFileListener)
    }

    private fun generatePackageNameDirectories(packageName: String) {
        var createdPackageName = ""
        val packageNameFragments = packageName.split(".")
        packageNameFragments.forEach {
            createdPackageName += "/$it"
            generateDirectory( "$generatedPath/src/main$createdPackageName", onGeneratedFileListener)
        }

        generateDirectory("$createdPackageName/config", onGeneratedFileListener)
        generateDirectory("$createdPackageName/errors", onGeneratedFileListener)
        generateDirectory("$createdPackageName/errors/payload", onGeneratedFileListener)
        generateDirectory("$createdPackageName/controllers", onGeneratedFileListener)
        generateDirectory("$createdPackageName/controllers/base", onGeneratedFileListener)
        generateDirectory("$createdPackageName/models", onGeneratedFileListener)
        generateDirectory("$createdPackageName/models/entities", onGeneratedFileListener)
        generateDirectory("$createdPackageName/models/repos", onGeneratedFileListener)
        generateDirectory("$createdPackageName/models/req", onGeneratedFileListener)
        generateDirectory("$createdPackageName/response", onGeneratedFileListener)
        generateDirectory("$createdPackageName/response/custom", onGeneratedFileListener)
        generateDirectory("$createdPackageName/service", onGeneratedFileListener)
        generateDirectory("$createdPackageName/service/base", onGeneratedFileListener)

        generateFile("JwtFilter", getFileContent("templates/spring/config/JwtFilter.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/config", onGeneratedFileListener)
        generateFile("JwtUtil", getFileContent("templates/spring/config/JwtUtil.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/config", onGeneratedFileListener)
        generateFile("MailManager", getFileContent("templates/spring/config/MailManager.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/config", onGeneratedFileListener)
        generateFile("RestAuthenticationEntryPoint", getFileContent("templates/spring/config/RestAuthenticationEntryPoint.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/config", onGeneratedFileListener)
        generateFile("SecurityConfiguration", getFileContent("templates/spring/config/SecurityConfiguration.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/config", onGeneratedFileListener)
        generateFile("WebMvcConfig", getFileContent("templates/spring/config/WebMvcConfig.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/config", onGeneratedFileListener)

        generateFile("AccountsController", getFileContent("templates/spring/controllers/AccountsController.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/controllers", onGeneratedFileListener)
        generateFile("BaseController", getFileContent("templates/spring/controllers/BaseController.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/controllers/base", onGeneratedFileListener)
        generateFile("BaseControllerImplementation", getFileContent("templates/spring/controllers/BaseControllerImplementation.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/controllers/base", onGeneratedFileListener)

        generateFile("UnknownErrorException", getFileContent("templates/spring/errors/UnknownErrorException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/errors", onGeneratedFileListener)
        generateFile("UnAuthException", getFileContent("templates/spring/errors/UnAuthException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/errors", onGeneratedFileListener)
        generateFile("InvalidDataException", getFileContent("templates/spring/errors/InvalidDataException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/errors", onGeneratedFileListener)
        generateFile("ErrorController", getFileContent("templates/spring/errors/ErrorController.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/errors", onGeneratedFileListener)
        generateFile("EntityNotFoundException", getFileContent("templates/spring/errors/EntityNotFoundException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/errors", onGeneratedFileListener)
        generateFile("BaseException", getFileContent("templates/spring/errors/BaseException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/errors", onGeneratedFileListener)
        generateFile("PayLoadError", getFileContent("templates/spring/errors/payload/PayLoadError.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/errors/payload", onGeneratedFileListener)
        generateFile("ErrorObjectResponse", getFileContent("templates/spring/errors/payload/ErrorObjectResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/errors/payload", onGeneratedFileListener)

        generateFile("Account", getFileContent("templates/spring/models/entities/account/Account.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models/entities/account", onGeneratedFileListener)
        generateFile("AccountAddress", getFileContent("templates/spring/models/entities/account/AccountAddress.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models/entities/account", onGeneratedFileListener)
        generateFile("AccountsPhoneNumber", getFileContent("templates/spring/models/entities/account/AccountsPhoneNumber.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models/entities/account", onGeneratedFileListener)
        generateFile("Test", getFileContent("templates/spring/models/entities/Test.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models/entities", onGeneratedFileListener)

        generateFile("AccountsPhoneNumberRepository", getFileContent("templates/spring/models/repos/AccountsPhoneNumberRepository.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models/repos", onGeneratedFileListener)
        generateFile("AccountsRepository", getFileContent("templates/spring/models/repos/AccountsRepository.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models/repos", onGeneratedFileListener)

        generateFile("OtpRequestBody", getFileContent("templates/spring/models/req/OtpRequestBody.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models/req", onGeneratedFileListener)
        generateFile("VerifyOtpBody", getFileContent("templates/spring/models/req/VerifyOtpBody.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models/req", onGeneratedFileListener)
        generateFile("BaseEntity", getFileContent("templates/spring/models/BaseEntity.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/models", onGeneratedFileListener)

        generateFile("AccountsService", getFileContent("templates/spring/service/AccountsService.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/service", onGeneratedFileListener)
        generateFile("ServiceImplementation", getFileContent("templates/spring/service/base/ServiceImplementation.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/service/base", onGeneratedFileListener)
        generateFile("BaseService", getFileContent("templates/spring/service/base/BaseService.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/service/base", onGeneratedFileListener)

        generateFile("SuccessResponse", getFileContent("templates/spring/response/SuccessResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/response", onGeneratedFileListener)
        generateFile("ResponseGenerator", getFileContent("templates/spring/response/ResponseGenerator.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/response", onGeneratedFileListener)
        generateFile("PageInfo", getFileContent("templates/spring/response/PageInfo.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/response", onGeneratedFileListener)
        generateFile("ListResponse", getFileContent("templates/spring/response/ListResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/response", onGeneratedFileListener)
        generateFile("ErrorResponse", getFileContent("templates/spring/response/ErrorResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/response", onGeneratedFileListener)
        generateFile("BaseResponse", getFileContent("templates/spring/response/BaseResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/response", onGeneratedFileListener)
        generateFile("AuthResponse", getFileContent("templates/spring/response/custom/AuthResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/response/custom", onGeneratedFileListener)
        generateFile("GeneralErrorResponse", getFileContent("templates/spring/response/custom/GeneralErrorResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$createdPackageName/response/custom", onGeneratedFileListener)
        
        generateFile("ServletInitializer", getFileContent("templates/spring/ServletInitializer.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, createdPackageName, onGeneratedFileListener)
        generateFile("AutowiredInjection", getFileContent("templates/spring/AutowiredInjection.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, createdPackageName, onGeneratedFileListener)
        generateFile("ApiConsts", getFileContent("templates/spring/ApiConsts.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, createdPackageName, onGeneratedFileListener)
        generateFile("ApiApplication", getFileContent("templates/spring/ApiApplication.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, createdPackageName, onGeneratedFileListener)
    }

    private fun generateRootFiles(group: String, description: String, version: String, artifact: String, name: String) {
        generateFile("mvnw", getFileContent("templates/spring/mvnw.cmd"), FileExtention.CMD, generatedPath, onGeneratedFileListener)
        generateFile("mvnw", getFileContent("templates/spring/mvnw"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
        generateFile(".gitignore", getFileContent("templates/spring/.gitignore"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
        generateFile("maven-wrapper", getFileContent("templates/spring/.mvn/wrapper/maven-wrapper.properties"), FileExtention.PROPERTIES, "$generatedPath/.mvn/wrapper", onGeneratedFileListener)
        generateFile("MavenWrapperDownloader", getFileContent("templates/spring/.mvn/wrapper/MavenWrapperDownloader.java"), FileExtention.JAVA, "$generatedPath/.mvn/wrapper", onGeneratedFileListener)
        generateFile("application", getFileContent("templates/spring/application.properties"), FileExtention.PROPERTIES, "$generatedPath/src/resources", onGeneratedFileListener)

        generateFile(
            "pom",
            getFileContent("templates/spring/pom.xml")
                .replace("#{Group}", group)
                .replace("#{Artifact}", artifact)
                .replace("#{Version}", version)
                .replace("#{Name}", name)
                .replace("#{Description}", description),
            FileExtention.XML,
            generatedPath,
            onGeneratedFileListener
        )
    }

}
