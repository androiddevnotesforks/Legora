package views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProjectGeneratorScreen(
    projectKey: String,
    generatedProjectPath: String,
    dependencies: ArrayList<ApplicationDependency>,
    fields: ArrayList<ProjectInformationItem>,
    nextRoute: Int,
    prevRoute: Int,
    onRouteChangeDestination: (Int) -> Unit,
) {
    val selectedProjectInformation = ApplicationProjectsManager.getProjectsList().filter { it.key.equals(projectKey) }
    val itemsPath = remember { mutableStateOf(listOf<String>()) }
    val (isListUpdated, setIsListUpdated) = remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        HeaderComponent()
        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(ApplicationStrings.PROJECT_GENERATOR_STARTED, fontSize = TextUnit(15f, TextUnitType.Sp), fontWeight = FontWeight.Bold)
                Text(ApplicationStrings.PROJECT_GENERATOR_STARTED_DESCRIPTION, fontSize = TextUnit(12f, TextUnitType.Sp))
                Spacer(Modifier.height(30.dp))

                Box(modifier = Modifier.clip(RoundedCornerShape(5.dp)).fillMaxWidth().background(Color.White)) {
                    Box(Modifier.padding(20.dp)) {
                        if (itemsPath.value.isEmpty()) {
                            Text("Click on Code Icon To Start Generating Project Source Code")
                        } else {
                            LazyColumn(modifier = Modifier.fillMaxWidth().wrapContentHeight(), verticalArrangement = Arrangement.Top) {
                                items(itemsPath.value) { item ->
                                    Text(item, modifier = Modifier.padding(5.dp))
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
                            CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Next Page") {
                                val applicationGenerator = ApplicationGeneratorManager(generatedProjectPath, { item ->
                                    itemsPath.value.toMutableList().also {
                                        println(it)
                                        it.add(item)
                                        setIsListUpdated(true)
                                    }
                                }) {
                                    onRouteChangeDestination(nextRoute)
                                }

                                applicationGenerator.generateProject(projectKey, dependencies, fields)
                            }
                        }
                    }
                }
            }
        }
    }
}
