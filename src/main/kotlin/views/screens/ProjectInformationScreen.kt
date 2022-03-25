package views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import models.ProjectInformationItem
import utils.ApplicationInformationManager
import utils.ApplicationProjectsManager
import utils.ApplicationStrings
import views.common.FooterComponent
import views.common.HeaderComponent
import views.components.ProjectCardComponent
import views.components.TextInputComponent

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProjectInformationScreen(projectKey: String, nextRoute: Int, prevRoute: Int, onRouteChangeDestination: (Int) -> Unit) {
    val selectedProjectInformation = ApplicationProjectsManager.getProjectsList().filter { it.key.equals(projectKey) }
    val projectFields = ApplicationInformationManager.getFieldsByProjectKey(projectKey)
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        HeaderComponent()
        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(ApplicationStrings.PROJECT_INFO_TITLE, fontSize = TextUnit(15f, TextUnitType.Sp), fontWeight = FontWeight.Bold)
                Text(ApplicationStrings.PROJECT_INFO_DES, fontSize = TextUnit(12f, TextUnitType.Sp))
            }

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Box(Modifier.fillMaxWidth(0.4f)) {
                    ProjectCardComponent(selectedProjectInformation[0], projectKey) {}
                }
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp), verticalArrangement = Arrangement.Top) {
            items(projectFields) { item ->
                if (item.type == ProjectInformationItem.TYPE_TEXT) {
                    TextInputComponent(item)
                } else if (item.type == ProjectInformationItem.TYPE_SWITCH) {

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
