package views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import models.ProjectItem
import utils.ApplicationColors
import views.common.ImageComponent


@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProjectCardComponent(projectItem: ProjectItem, selectedProjectKey: String, onItemClickListener: (ProjectItem) -> Unit) {
    val shape = RoundedCornerShape(5.dp)
    val modifier = if (projectItem.key.equals(selectedProjectKey)) {
        Modifier.clip(shape).border(BorderStroke(width = 2.dp, color = ApplicationColors.PRIMARY_COLOR)).clickable { onItemClickListener(projectItem) }
    } else {
        Modifier.clip(shape).clickable { onItemClickListener(projectItem) }
    }

    Box(modifier = Modifier.padding(10.dp)) {
        Box(modifier = modifier) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clip(shape).background(Color.White)) {
                Spacer(modifier = Modifier.padding(bottom = 15.dp))
                ImageComponent(projectItem.icon, projectItem.description, Modifier.width(90.dp).height(90.dp))
                Spacer(modifier = Modifier.padding(bottom = 15.dp))
                Text(projectItem.title, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), maxLines = 1, fontSize = TextUnit(15f, TextUnitType.Sp))
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                Text(projectItem.description, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), maxLines = 1, fontSize = TextUnit(12f, TextUnitType.Sp))
                Spacer(modifier = Modifier.padding(bottom = 15.dp))
            }
        }
    }
}
