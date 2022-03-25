package views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import utils.ApplicationProjectsManager
import utils.ApplicationStrings
import views.common.FooterComponent
import views.common.HeaderComponent
import views.components.ProjectCardComponent

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProjectInformationScreen(projectKey: String, nextRoute: Int, prevRoute: Int, onRouteChangeDestination: (Int) -> Unit) {
    val selectedProjectInformation = ApplicationProjectsManager.getProjectsList().filter { it.key.equals(projectKey) }
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        HeaderComponent()
        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(ApplicationStrings.PROJECT_INFO_TITLE, fontSize = TextUnit(15f, TextUnitType.Sp), fontWeight = FontWeight.Bold)
                Text(ApplicationStrings.PROJECT_INFO_DES, fontSize = TextUnit(12f, TextUnitType.Sp))
            }

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Box(Modifier.fillMaxWidth(0.4f)) {
                    ProjectCardComponent(selectedProjectInformation.get(0), projectKey) {}
                }
            }
        }
    }

    FooterComponent({
        onRouteChangeDestination(nextRoute)
    }, {
        onRouteChangeDestination(prevRoute)
    })
}