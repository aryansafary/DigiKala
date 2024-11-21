package com.arysapp.digikala.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.arysapp.digikala.ui.screens.BasketScreen
import com.arysapp.digikala.ui.screens.CategoryScreen
import com.arysapp.digikala.ui.screens.home.HomeScreen
import com.arysapp.digikala.ui.screens.ProfileScreen
import com.arysapp.digikala.ui.screens.SplashScreen
import com.arysapp.digikala.ui.screens.home.WebPageScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    )
    {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screens.CategoryScreen.route) {
            CategoryScreen(navController = navController)
        }
        composable(route = Screens.BasketScreen.route) {
            BasketScreen(navController = navController)
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screens.WebPageScreen.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreen(navController = navController, url = url)
            }
        }


    }

}
