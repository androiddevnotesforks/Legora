package views.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import utils.ApplicationColors
import utils.ApplicationProjectsManager
import utils.ApplicationStrings
import views.common.HeaderComponent
import views.components.ProjectCardComponent
import androidx.compose.ui.window.*
import androidx.compose.runtime.*

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen() {

    }
}

@OptIn(ExperimentalUnitApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(onNextScreenRequest: () -> Unit) {
    val projectsList = ApplicationProjectsManager.getProjectsList()
    var selectedProjectKey by remember { mutableStateOf("") }
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
                        ProjectCardComponent(item, selectedProjectKey) {
                            selectedProjectKey = it.key
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(0.4f).fillMaxHeight().padding(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    Row {
                        Text("`geretg")
                        Text("`geretg")
                    }
                }
            }
        }
    }
}
