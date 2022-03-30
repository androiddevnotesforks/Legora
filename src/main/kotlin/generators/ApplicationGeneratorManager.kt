package generators

import models.ApplicationDependency
import models.ProjectInformationItem
import java.util.concurrent.TimeUnit

class ApplicationGeneratorManager constructor(
    private val onGeneratedFileListener: (String) -> Unit,
    private val onProjectFinishedListener: () -> Unit
) {

    fun generateProject(
        projectKey: String,
        dependencies: ArrayList<ApplicationDependency>,
        fields: ArrayList<ProjectInformationItem>
    ) {
        onGeneratedFileListener("ajeksfnsrf")
        TimeUnit.SECONDS.sleep(1);
        onGeneratedFileListener("ajeksfnsrf")
        TimeUnit.SECONDS.sleep(1);
        onGeneratedFileListener("ajeksfnsrf")
        TimeUnit.SECONDS.sleep(1);
        onGeneratedFileListener("ajeksfnsrf")
        TimeUnit.SECONDS.sleep(1);
    }

}