package generators.base

import models.ApplicationDependency
import models.ProjectInformationItem

interface ProjectGeneratorImplementation {

    fun generateProject(
        dependencies: ArrayList<ApplicationDependency>,
        fields: HashMap<String, String>
    )

}
