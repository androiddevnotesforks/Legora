package views.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import utils.ApplicationColors
import utils.ApplicationIcons
import views.components.CircleIconComponent

@Preview
@Composable
fun FooterComponentPreview() {
    FooterComponent({

    }, {

    })
}

@Composable
fun FooterComponent(onNextClickListener: () -> Unit, onBackClickListener: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().background(ApplicationColors.GRAY_COLOR).wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(20.dp).wrapContentHeight().wrapContentWidth(Alignment.Start)
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
            modifier = Modifier.padding(20.dp).wrapContentHeight().wrapContentWidth(Alignment.End)
        ) {
            CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Next Page") {
                onNextClickListener()
            }
        }
    }
}
