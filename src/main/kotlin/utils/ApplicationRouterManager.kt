package utils

import errors.RouterNotFoundException
import models.ApplicationRoute

object ApplicationRouterManager {

    fun getRouterInformationByRouterKey(key: Int): ApplicationRoute {
        return when (key) {
            ApplicationRoute.NO_SCREEN_POSITION -> throw RouterNotFoundException()
            ApplicationRoute.MAIN_SCREEN_ROUTE -> ApplicationRoute.MainScreenRouter()
            ApplicationRoute.SPLASH_SCREEN_ROUTE -> ApplicationRoute.SplashScreenRouter()
            else -> throw RouterNotFoundException()
        }
    }

    fun getDefaultRouter(): ApplicationRoute {
        return ApplicationRoute.SplashScreenRouter()
    }

}
