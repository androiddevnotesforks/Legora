package views.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import utils.ApplicationColors
import java.util.concurrent.TimeUnit

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen() {
        println("Logger: Splash Screen Started ...")
    }
}

@Composable
fun SplashScreen(onScreenStarted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(ApplicationColors.PRIMARY_COLOR)
    ) {
        Text("Hellos, Welcome")
        TimeUnit.SECONDS.sleep(2);
        onScreenStarted()
    }
}