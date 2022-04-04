package views.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import utils.ApplicationColors
import utils.ApplicationProjectsManager
import utils.ApplicationStrings
import views.common.HeaderComponent
import views.components.ProjectCardComponent
import utils.ApplicationIcons
import views.components.CircleIconComponent

@OptIn(ExperimentalUnitApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(selectedProject: String, onNextScreenRequest: (String) -> Unit) {
    val projectsList = ApplicationProjectsManager.getProjectsList()
    val selectedProjectKey = remember { mutableStateOf(selectedProject) }
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(ApplicationColors.GRAY_COLOR)) {
        HeaderComponent()
        Column(modifier = Modifier.padding(20.dp)) {
            Text(ApplicationStrings.MAIN_SCREEN_TITLE, fontSize = TextUnit(17f, TextUnitType.Sp), fontWeight = FontWeight.Bold)
            Text(ApplicationStrings.MAIN_SCREEN_DESCRIPTION, fontSize = TextUnit(14f, TextUnitType.Sp), modifier = Modifier.padding(top = 10.dp))
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(5),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(projectsList) { item ->
                        ProjectCardComponent(item, selectedProjectKey.value) {
                            if (it.key.equals(selectedProjectKey.value)) {
                                selectedProjectKey.value = ""
                                return@ProjectCardComponent
                            }

                            selectedProjectKey.value = it.key
                        }
                    }
                }
            }

            if (selectedProjectKey.value.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth().fillMaxHeight()
                    ) {
                        Row {
//                            CircleIconComponent(ApplicationIcons.INFO_ICON, "Application Information") {
//
//                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Next Page") {
                                onNextScreenRequest(selectedProjectKey.value)
                            }
                        }
                    }
                }
            }
        }
    }
}
