package com.arysapp.digikala.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arysapp.digikala.R
import com.arysapp.digikala.navigation.Screens
import com.arysapp.digikala.ui.componets.Loading3Dots
import com.arysapp.digikala.ui.theme.splashBg
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
Splash()
LaunchedEffect(true) {
    delay(3500)
    navController.navigate(Screens.HomeScreen.route) {
        popUpTo(Screens.SplashScreen.route) {
            inclusive = true
        }
    }
    }
}


@Composable
fun Splash(){
    Box(modifier =
    Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.splashBg),
        contentAlignment = Alignment.Center
    ){
Image(
    modifier = Modifier.size(250.dp),
    painter = painterResource(id = R.drawable.digikala_logo) ,
    contentDescription ="Logo" )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(100.dp),
       contentAlignment = Alignment.BottomCenter
        ){
        Image(
            modifier = Modifier.height(30.dp),
            painter = painterResource(id = R.drawable.digikala_txt_white) ,
            contentDescription ="TextLogo" )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.BottomCenter
    ){
Loading3Dots(isDark = false)
    }
}