package views.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import utils.ApplicationColors
import java.util.concurrent.TimeUnit
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

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
            val bitmap = useResource("icon_white.png") { loadImageBitmap(it) }
            Image(
                bitmap = bitmap,
                "Legora Application Logo",
                alignment = Alignment.Center,
                modifier = Modifier.width(100.dp).height(100.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text("Legora", color = Color.White, fontSize = TextUnit(20f, TextUnitType.Sp))
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(10.dp).align(Alignment.BottomStart)
        ) {
            Text("Generate Project Configuration, Source Code Directly", color = Color.White, fontSize = TextUnit(17f, TextUnitType.Sp))
            Text("Specify Project Type, Information Without Opening IDE", color = Color.White, fontSize = TextUnit(15f, TextUnitType.Sp))
        }
    }

    TimeUnit.SECONDS.sleep(2);
    onScreenStarted()
}