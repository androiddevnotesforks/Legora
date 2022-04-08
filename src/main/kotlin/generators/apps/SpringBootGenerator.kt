package generators.apps

import generators.base.ProjectGeneratorImplementation
import generators.base.TemplatingFilesGenerator
import models.ApplicationDependency

class SpringBootGenerator constructor(
    private val isJavaLanguage: Boolean,
    private val generatedPath: String,
    private val onGeneratedFileListener: (String) -> Unit
): TemplatingFilesGenerator(), ProjectGeneratorImplementation {

    override fun generateProject(dependencies: ArrayList<ApplicationDependency>, fields: HashMap<String, String>) {

    }

}
