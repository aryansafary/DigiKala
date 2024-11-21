package com.arysapp.digikala.navigation


sealed class Screens(val route: String) {
    data object SplashScreen : Screens("SplashScreen")
    data object HomeScreen : Screens("HomeScreen")
    data object CategoryScreen : Screens("CategoryScreen")
    data object BasketScreen : Screens("BasketScreen")
    data object ProfileScreen : Screens("ProfileScreen")
    data object WebPageScreen : Screens("WebPageScreen")

    fun whitArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}

