package views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.ApplicationDependency
import utils.ApplicationColors

@Composable
fun ProjectDependencyComponent(dependency: ApplicationDependency, onItemClickListener: (ApplicationDependency) -> Unit) {
    val shape = RoundedCornerShape(5.dp)
    val isDependencySelected = remember { mutableStateOf(!dependency.isRemovable || dependency.isSelected) }
    val modifier = if (isDependencySelected.value) {
        Modifier.padding(15.dp).clip(shape).border(BorderStroke(width = 2.dp, color = ApplicationColors.PRIMARY_COLOR)).clickable {
            onItemClickListener(dependency)
            if (dependency.isRemovable) {
                dependency.isSelected = !dependency.isSelected
                isDependencySelected.value = dependency.isSelected
            }
        }
    } else {
        Modifier.padding(15.dp).clip(shape).clickable {
            onItemClickListener(dependency)
            if (dependency.isRemovable) {
                dependency.isSelected = !dependency.isSelected
                isDependencySelected.value = dependency.isSelected
            }
        }
    }

    Box(modifier = modifier) {
        Box(Modifier.background(Color.White).padding(10.dp)) {
            Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(dependency.dependency)
                Spacer(modifier = Modifier.width(200.dp))
                Text(dependency.version)
            }
        }
    }
}
