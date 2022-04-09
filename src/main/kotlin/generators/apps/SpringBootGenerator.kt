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
            generateDirectory( "$generatedPath/src/main/kotlin$createdPackageName", onGeneratedFileListener)
        }

        val generateSource = "$generatedPath/src/main/kotlin$createdPackageName"
        generateDirectory("$generateSource/config", onGeneratedFileListener)
        generateDirectory("$generateSource/errors", onGeneratedFileListener)
        generateDirectory("$generateSource/errors/payload", onGeneratedFileListener)
        generateDirectory("$generateSource/controllers", onGeneratedFileListener)
        generateDirectory("$generateSource/controllers/base", onGeneratedFileListener)
        generateDirectory("$generateSource/models", onGeneratedFileListener)
        generateDirectory("$generateSource/models/entities", onGeneratedFileListener)
        generateDirectory("$generateSource/models/repos", onGeneratedFileListener)
        generateDirectory("$generateSource/models/req", onGeneratedFileListener)
        generateDirectory("$generateSource/response", onGeneratedFileListener)
        generateDirectory("$generateSource/response/custom", onGeneratedFileListener)
        generateDirectory("$generateSource/service", onGeneratedFileListener)
        generateDirectory("$generateSource/service/base", onGeneratedFileListener)

        generateFile("JwtFilter", getFileContent("templates/spring/config/JwtFilter.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/config", onGeneratedFileListener)
        generateFile("JwtUtil", getFileContent("templates/spring/config/JwtUtil.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/config", onGeneratedFileListener)
        generateFile("MailManager", getFileContent("templates/spring/config/MailManager.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/config", onGeneratedFileListener)
        generateFile("RestAuthenticationEntryPoint", getFileContent("templates/spring/config/RestAuthenticationEntryPoint.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/config", onGeneratedFileListener)
        generateFile("SecurityConfiguration", getFileContent("templates/spring/config/SecurityConfiguration.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/config", onGeneratedFileListener)
        generateFile("WebMvcConfig", getFileContent("templates/spring/config/WebMvcConfig.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/config", onGeneratedFileListener)

        generateFile("AccountsController", getFileContent("templates/spring/controllers/AccountsController.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/controllers", onGeneratedFileListener)
        generateFile("BaseController", getFileContent("templates/spring/controllers/base/BaseController.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/controllers/base", onGeneratedFileListener)
        generateFile("BaseControllerImplementation", getFileContent("templates/spring/controllers/base/BaseControllerImplementation.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/controllers/base", onGeneratedFileListener)

        generateFile("UnknownErrorException", getFileContent("templates/spring/errors/UnknownErrorException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/errors", onGeneratedFileListener)
        generateFile("UnAuthException", getFileContent("templates/spring/errors/UnAuthException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/errors", onGeneratedFileListener)
        generateFile("InvalidDataException", getFileContent("templates/spring/errors/InvalidDataException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/errors", onGeneratedFileListener)
        generateFile("ErrorController", getFileContent("templates/spring/errors/ErrorController.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/errors", onGeneratedFileListener)
        generateFile("EntityNotFoundException", getFileContent("templates/spring/errors/EntityNotFoundException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/errors", onGeneratedFileListener)
        generateFile("BaseException", getFileContent("templates/spring/errors/BaseException.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/errors", onGeneratedFileListener)
        generateFile("PayLoadError", getFileContent("templates/spring/errors/payload/PayLoadError.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/errors/payload", onGeneratedFileListener)
        generateFile("ErrorObjectResponse", getFileContent("templates/spring/errors/payload/ErrorObjectResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/errors/payload", onGeneratedFileListener)

        generateFile("Account", getFileContent("templates/spring/models/entities/account/Account.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models/entities/account", onGeneratedFileListener)
        generateFile("AccountAddress", getFileContent("templates/spring/models/entities/account/AccountAddress.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models/entities/account", onGeneratedFileListener)
        generateFile("AccountsPhoneNumber", getFileContent("templates/spring/models/entities/account/AccountsPhoneNumber.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models/entities/account", onGeneratedFileListener)
        generateFile("Test", getFileContent("templates/spring/models/entities/Test.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models/entities", onGeneratedFileListener)

        generateFile("AccountsPhoneNumberRepository", getFileContent("templates/spring/models/repos/AccountsPhoneNumberRepository.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models/repos", onGeneratedFileListener)
        generateFile("AccountsRepository", getFileContent("templates/spring/models/repos/AccountsRepository.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models/repos", onGeneratedFileListener)

        generateFile("OtpRequestBody", getFileContent("templates/spring/models/req/OtpRequestBody.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models/req", onGeneratedFileListener)
        generateFile("VerifyOtpBody", getFileContent("templates/spring/models/req/VerifyOtpBody.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models/req", onGeneratedFileListener)
        generateFile("BaseEntity", getFileContent("templates/spring/models/BaseEntity.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/models", onGeneratedFileListener)

        generateFile("AccountsService", getFileContent("templates/spring/service/AccountsService.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/service", onGeneratedFileListener)
        generateFile("ServiceImplementation", getFileContent("templates/spring/service/base/ServiceImplementation.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/service/base", onGeneratedFileListener)
        generateFile("BaseService", getFileContent("templates/spring/service/base/BaseService.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/service/base", onGeneratedFileListener)

        generateFile("SuccessResponse", getFileContent("templates/spring/response/SuccessResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/response", onGeneratedFileListener)
        generateFile("ResponseGenerator", getFileContent("templates/spring/response/ResponseGenerator.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/response", onGeneratedFileListener)
        generateFile("PaginationResponse", getFileContent("templates/spring/response/PaginationResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/response", onGeneratedFileListener)
        generateFile("ListResponse", getFileContent("templates/spring/response/ListResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/response", onGeneratedFileListener)
        generateFile("ErrorResponse", getFileContent("templates/spring/response/ErrorResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/response", onGeneratedFileListener)
        generateFile("BaseResponse", getFileContent("templates/spring/response/BaseResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/response", onGeneratedFileListener)
        generateFile("AuthResponse", getFileContent("templates/spring/response/custom/AuthResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/response/custom", onGeneratedFileListener)
        generateFile("GeneralErrorResponse", getFileContent("templates/spring/response/custom/GeneralErrorResponse.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, "$generateSource/response/custom", onGeneratedFileListener)

        generateFile("ServletInitializer", getFileContent("templates/spring/ServletInitializer.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, generateSource, onGeneratedFileListener)
        generateFile("AutowiredInjection", getFileContent("templates/spring/AutowiredInjection.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, generateSource, onGeneratedFileListener)
        generateFile("ApiConsts", getFileContent("templates/spring/ApiConsts.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, generateSource, onGeneratedFileListener)
        generateFile("ApiApplication", getFileContent("templates/spring/ApiApplication.kt").replace("#{PackageName}", packageName), FileExtention.KOTLIN, generateSource, onGeneratedFileListener)
    }

    private fun generateRootFiles(group: String, description: String, version: String, artifact: String, name: String) {
        generateFile("mvnw", getFileContent("templates/spring/mvnw.cmd"), FileExtention.CMD, generatedPath, onGeneratedFileListener)
        generateFile("mvnw", getFileContent("templates/spring/mvnw"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
        generateFile(".gitignore", getFileContent("templates/spring/gitignore"), FileExtention.EMPTY, generatedPath, onGeneratedFileListener)
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
