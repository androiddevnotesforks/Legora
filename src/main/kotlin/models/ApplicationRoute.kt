package models

sealed class ApplicationRoute(val route: Int, val prevRoute: Int, val nextRoute: Int) {

    companion object {
        const val NO_SCREEN_POSITION = 0
        const val SPLASH_SCREEN_ROUTE = 1
        const val MAIN_SCREEN_ROUTE = 2
        const val APP_INFO_SCREEN_ROUTE = 3
    }

    class SplashScreenRouter: ApplicationRoute(SPLASH_SCREEN_ROUTE, NO_SCREEN_POSITION, MAIN_SCREEN_ROUTE)
    class MainScreenRouter: ApplicationRoute(MAIN_SCREEN_ROUTE, NO_SCREEN_POSITION, APP_INFO_SCREEN_ROUTE)

}
