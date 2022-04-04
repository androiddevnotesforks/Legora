package views.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import utils.ApplicationColors
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import utils.ApplicationIcons
import utils.ApplicationStrings
import views.components.CircleColorIconComponent

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen() {
        println("Logger: Splash Screen Started ...")
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SplashScreen(onScreenStarted: () -> Unit) {
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
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(10.dp).align(Alignment.BottomStart)
        ) {
            Text(ApplicationStrings.SPLASH_SCREEN_TITLE, color = Color.White, fontSize = TextUnit(17f, TextUnitType.Sp))
            Text(ApplicationStrings.SPLASH_SCREEN_DESCRIPTION, color = Color.White, fontSize = TextUnit(15f, TextUnitType.Sp))
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(10.dp).align(Alignment.BottomEnd)
        ) {
            CircleColorIconComponent(ApplicationIcons.NEXT_ARROW_RED, "Next Button", Color.White) {
                onScreenStarted()
            }
        }
    }
}