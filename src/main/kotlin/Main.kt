// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import models.ApplicationRoute
import utils.ApplicationRouterManager
import utils.ApplicationStrings
import views.screens.MainScreen
import views.screens.SplashScreen
import kotlin.system.exitProcess

@Composable
@Preview
fun App() {
    var routerInfoState by remember {
        mutableStateOf(ApplicationRouterManager.getDefaultRouter())
    }

    DesktopMaterialTheme {
        when (routerInfoState) {
            is ApplicationRoute.SplashScreenRouter -> SplashScreen {
                routerInfoState = ApplicationRouterManager.getRouterInformationByRouterKey(routerInfoState.nextRoute)
            }

            is ApplicationRoute.MainScreenRouter -> MainScreen {
                routerInfoState = ApplicationRouterManager.getRouterInformationByRouterKey(routerInfoState.nextRoute)
            }
        }
    }
}

fun main() = application {
    Window(
        state = WindowState(size = WindowSize(1300.dp, 720.dp)),
        onCloseRequest = {
           exitProcess(0)
        },
        title = ApplicationStrings.APP_NAME,
        resizable = true
    ) {
        App()
    }
}
