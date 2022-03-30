package views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import models.ApplicationDependency
import models.ProjectInformationItem
import utils.*
import views.common.FooterComponent
import views.common.HeaderComponent
import views.components.*

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProjectDependenciesScreen(
    projectKey: String,
    nextRoute: Int,
    prevRoute: Int,
    onRouteChangeDestination: (Int) -> Unit,
    onDependenciesResult: (ArrayList<ApplicationDependency>) -> Unit
) {
    val selectedProjectInformation = ApplicationProjectsManager.getProjectsList().filter { it.key.equals(projectKey) }
    val projectDependencies = ApplicationDependenciesManager.getProjectDependenciesByKey(projectKey)
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        HeaderComponent()
        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(ApplicationStrings.PROJECT_INFO_TITLE, fontSize = TextUnit(15f, TextUnitType.Sp), fontWeight = FontWeight.Bold)
                Text(ApplicationStrings.PROJECT_INFO_DES, fontSize = TextUnit(12f, TextUnitType.Sp))
                Spacer(Modifier.height(10.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth().wrapContentHeight(), verticalArrangement = Arrangement.Top) {
                    items(projectDependencies) { item ->
                        ProjectDependencyComponent(item) {

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
                            Box(modifier = Modifier.rotate(180f)) {
                                CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Prev Page") {
                                    onDependenciesResult(projectDependencies)
                                    onRouteChangeDestination(prevRoute)
                                }
                            }

                            Spacer(modifier = Modifier.width(20.dp))
                            CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Next Page") {
                                onRouteChangeDestination(nextRoute)
                            }
                        }
                    }
                }
            }
        }
    }
}
