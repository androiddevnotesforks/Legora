// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import models.ApplicationDependency
import models.ApplicationRoute
import utils.ApplicationRouterManager
import utils.ApplicationStrings
import views.screens.*
import kotlin.system.exitProcess

@Composable
@Preview
fun App() {
    var routerInfoState by remember { mutableStateOf(ApplicationRouterManager.getDefaultRouter()) }
    val routerValue = remember { mutableStateOf(routerInfoState.route) }
    val dependenciesValue = remember { mutableStateOf(arrayListOf<ApplicationDependency>()) }
    val selectedProject = remember { mutableStateOf("") }
    val generatedPath = remember { mutableStateOf("") }
    DesktopMaterialTheme {
        when (routerInfoState) {
            is ApplicationRoute.SplashScreenRouter -> SplashScreen {
                routerInfoState = ApplicationRouterManager.getRouterInformationByRouterKey(routerInfoState.nextRoute)
                routerValue.value = routerInfoState.route
            }

            is ApplicationRoute.MainScreenRouter -> MainScreen(selectedProject.value) {
                selectedProject.value = it
                routerInfoState = ApplicationRouterManager.getRouterInformationByRouterKey(routerInfoState.nextRoute)
            }

            is ApplicationRoute.ApplicationInfoRouter -> ProjectInformationScreen(selectedProject.value, routerInfoState.nextRoute, routerInfoState.prevRoute) {
                routerInfoState = ApplicationRouterManager.getRouterInformationByRouterKey(it)
                routerValue.value = routerInfoState.route
            }

            is ApplicationRoute.ApplicationDependenciesRouter -> ProjectDependenciesScreen(selectedProject.value, routerInfoState.nextRoute, routerInfoState.prevRoute, {
                routerValue.value = it
                routerInfoState = ApplicationRouterManager.getRouterInformationByRouterKey(routerInfoState.nextRoute)
            }) {
                dependenciesValue.value = it
            }

            is ApplicationRoute.ApplicationPathPickerRouter -> GeneratedPathPickerScreen(selectedProject.value, routerInfoState.nextRoute, routerInfoState.prevRoute) { destination: Int, path: String ->
                generatedPath.value = path
                routerValue.value = destination
                routerInfoState = ApplicationRouterManager.getRouterInformationByRouterKey(routerInfoState.nextRoute)
            }
        }
    }
}

fun main() = application {
    Window(
        state = WindowState(size = WindowSize(1600.dp, 820.dp)),
        title = ApplicationStrings.APP_NAME,
        resizable = true,
        onCloseRequest = {
            exitProcess(0)
        },
    ) {
        App()
    }
}
