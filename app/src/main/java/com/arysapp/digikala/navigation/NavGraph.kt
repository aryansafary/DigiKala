package com.arysapp.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arysapp.digikala.ui.screens.BasketScreen
import com.arysapp.digikala.ui.screens.CategoryScreen
import com.arysapp.digikala.ui.screens.HomeScreen
import com.arysapp.digikala.ui.screens.ProfileScreen
import com.arysapp.digikala.ui.screens.SplashScreen

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
    }
}