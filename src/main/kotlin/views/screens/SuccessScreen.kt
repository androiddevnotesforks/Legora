package views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import utils.ApplicationColors
import utils.ApplicationIcons
import utils.ApplicationStrings
import views.components.CircleColorIconComponent
import kotlin.system.exitProcess

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SuccessScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(ApplicationColors.PRIMARY_COLOR)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val bitmap = useResource(ApplicationIcons.LOGO_WHITE) { loadImageBitmap(it) }
            Image(
                bitmap = bitmap,
                ApplicationStrings.APP_LOGO,
                alignment = Alignment.Center,
                modifier = Modifier.width(100.dp).height(100.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(ApplicationStrings.APP_NAME, color = Color.White, fontSize = TextUnit(20f, TextUnitType.Sp))
            Spacer(Modifier.height(10.dp))
            Text(ApplicationStrings.APP_SUCCESS, color = Color.White, fontSize = TextUnit(15f, TextUnitType.Sp))
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(10.dp).align(Alignment.BottomEnd)
        ) {
            CircleColorIconComponent(ApplicationIcons.NEXT_ARROW_RED, "Next Button", Color.White) {
                exitProcess(0)
            }
        }
    }
}