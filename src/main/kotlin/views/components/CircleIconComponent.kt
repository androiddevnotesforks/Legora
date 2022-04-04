package views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import utils.ApplicationColors
import views.common.ImageComponent

@Composable
fun CircleIconComponent(path: String, description: String, onItemClickListener: () -> Unit) {
    Row(
        modifier = Modifier.clip(CircleShape).background(ApplicationColors.PRIMARY_COLOR).padding(15.dp).clickable { onItemClickListener() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageComponent(path, description, Modifier.width(20.dp).height(20.dp))
    }
}

@Composable
fun CircleColorIconComponent(path: String, description: String, color: Color, onItemClickListener: () -> Unit) {
    Row(
        modifier = Modifier.clip(CircleShape).background(color).padding(15.dp).clickable { onItemClickListener() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageComponent(path, description, Modifier.width(20.dp).height(20.dp))
    }
}