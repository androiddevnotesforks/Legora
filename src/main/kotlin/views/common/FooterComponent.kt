package views.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import utils.ApplicationIcons
import views.components.CircleIconComponent

@Composable
fun FooterComponent(onNextClickListener: () -> Unit, onBackClickListener: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp)
    ) {
        Box(modifier = Modifier.rotate(180f)) {
            CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Prev Page") {
                onBackClickListener()
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp)
    ) {
        CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Next Page") {
            onNextClickListener()
        }
    }
}
