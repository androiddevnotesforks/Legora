package views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import generators.ApplicationGeneratorManager
import models.ApplicationDependency
import models.ProjectInformationItem
import utils.ApplicationIcons
import utils.ApplicationProjectsManager
import utils.ApplicationStrings
import views.common.HeaderComponent
import views.components.CircleIconComponent
import views.components.ProjectCardComponent

@OptIn(ExperimentalUnitApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ProjectGeneratorScreen(
    projectKey: String,
    generatedProjectPath: String,
    dependencies: ArrayList<ApplicationDependency>,
    fields: ArrayList<ProjectInformationItem>,
    nextRoute: Int,
    prevRoute: Int,
    paths: ArrayList<String> = arrayListOf(),
    isListUpdatedInitValue: Boolean = false,
    onRouteChangeDestination: (Int) -> Unit,
) {
    val selectedProjectInformation = ApplicationProjectsManager.getProjectsList().filter { it.key.equals(projectKey) }
    val itemsPath = remember { mutableStateOf(paths) }
    val isListUpdated = remember { mutableStateOf(GeneratorState(isListUpdatedInitValue, false)) }
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        HeaderComponent()
        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(
                    ApplicationStrings.PROJECT_GENERATOR_STARTED,
                    fontSize = TextUnit(15f, TextUnitType.Sp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    ApplicationStrings.PROJECT_GENERATOR_STARTED_DESCRIPTION,
                    fontSize = TextUnit(12f, TextUnitType.Sp)
                )
                Spacer(Modifier.height(30.dp))

                Box(modifier = Modifier.clip(RoundedCornerShape(5.dp)).fillMaxWidth().background(Color.White)) {
                    Column {
                        Box(Modifier.padding(20.dp)) {
                            Text("Click on Code Icon To Start Generating Project Source Code")
                            if (!isListUpdated.value.isListUpdated) {
                                Text("Click on Code Icon To Start Generating Project Source Code", modifier = Modifier.height(0.dp))
                            }

                            Column(
                                modifier = Modifier.padding(top = 35.dp).fillMaxWidth().wrapContentHeight().verticalScroll(scrollState),
                                verticalArrangement = Arrangement.Top
                            ) {
                                itemsPath.value.reversed().forEach {
                                    Text(it, modifier = Modifier.padding(bottom = 5.dp))
                                }
                            }
                        }
                    }
                }
            }

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Box(Modifier.fillMaxWidth(0.4f)) {
                    ProjectCardComponent(selectedProjectInformation[0], projectKey) {}
                }

                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth().fillMaxHeight()
                    ) {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            if (!isListUpdated.value.isGeneratorStarted) {
                                CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Next Page") {
                                    Thread {
                                        ApplicationGeneratorManager.generateProject(
                                            projectKey,
                                            dependencies,
                                            fields,
                                            generatedProjectPath,
                                            { item ->
                                                paths.add(item)
                                                isListUpdated.value = GeneratorState(!isListUpdated.value.isListUpdated, true)
                                            }) {
                                            onRouteChangeDestination(nextRoute)
                                        }
                                    }.start()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class GeneratorState(
    var isListUpdated: Boolean = false,
    var isGeneratorStarted: Boolean = false
)