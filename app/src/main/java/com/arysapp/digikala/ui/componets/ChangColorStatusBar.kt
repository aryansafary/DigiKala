@file:Suppress("DEPRECATION")

package com.arysapp.digikala.ui.componets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arysapp.digikala.navigation.Screens
import com.arysapp.digikala.ui.theme.Purple500
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChangColorStatusBar(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()
    if(navBackStackEntry?.destination?.route==Screens.SplashScreen.route){
    SideEffect {
        systemUiController.setSystemBarsColor(Purple500)
    }
    }else{
        SideEffect {
            systemUiController.setSystemBarsColor(Color.White)
        }
    }
}