package com.arysapp.digikala.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.Purple200

@Composable
fun ChangeStatusBarColor(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()


    val statusBarColor = if (MaterialTheme.colors.isLight) {
        Color.White
    } else {
        Color.Black
    }

    when (navBackStackEntry?.destination?.route) {
        Screen.Splash.route -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Purple200
                )
            }
        }
        else -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = statusBarColor
                )
            }
        }
    }
}