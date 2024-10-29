package com.arysapp.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arysapp.digikala.navigation.SetupNavGraph
import com.arysapp.digikala.ui.theme.DigiKalaTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           navController = rememberNavController()
            DigiKalaTheme {
                Scaffold(
                    bottomBar = {
                        //Todo bottom bar
                    }
                ){
                  SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

