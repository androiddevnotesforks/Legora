package views.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import models.ProjectInformationItem
import models.ProjectItem
import utils.ApplicationIcons
import utils.ApplicationInformationManager
import utils.ApplicationProjectsManager
import utils.ApplicationStrings
import views.common.FooterComponent
import views.common.HeaderComponent
import views.components.CircleIconComponent
import views.components.ProjectCardComponent
import views.components.SwitchInputComponent
import views.components.TextInputComponent

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProjectInformationScreen(projectKey: String, nextRoute: Int, prevRoute: Int, onRouteChangeDestination: (Int, ArrayList<ProjectInformationItem>) -> Unit) {
    val selectedProjectInformation = ApplicationProjectsManager.getProjectsList().filter { it.key.equals(projectKey) }
    val projectFields = ApplicationInformationManager.getFieldsByProjectKey(projectKey)
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        HeaderComponent()
        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(ApplicationStrings.PROJECT_INFO_TITLE, fontSize = TextUnit(15f, TextUnitType.Sp), fontWeight = FontWeight.Bold)
                Text(ApplicationStrings.PROJECT_INFO_DES, fontSize = TextUnit(12f, TextUnitType.Sp))
                Spacer(Modifier.height(10.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth().wrapContentHeight(), verticalArrangement = Arrangement.Top) {
                    items(projectFields) { item ->
                        if (item.type == ProjectInformationItem.TYPE_TEXT) {
                            TextInputComponent(item)
                        } else if (item.type == ProjectInformationItem.TYPE_SWITCH) {
                            SwitchInputComponent(item)
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
                                    onRouteChangeDestination(prevRoute, projectFields)
                                }
                            }

                            Spacer(modifier = Modifier.width(20.dp))
                            CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Next Page") {
                                onRouteChangeDestination(nextRoute, projectFields)
                            }
                        }
                    }
                }
            }
        }
    }
}
