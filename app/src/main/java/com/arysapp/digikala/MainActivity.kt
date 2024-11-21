package com.arysapp.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arysapp.digikala.navigation.BottomNavigationBar
import com.arysapp.digikala.navigation.SetupNavGraph
import com.arysapp.digikala.ui.componets.AppConfig
import com.arysapp.digikala.ui.componets.ChangColorStatusBar
import com.arysapp.digikala.ui.theme.DigiKalaTheme
import com.arysapp.digikala.util.Constants.PERSIAN_LANGUAGE
import com.arysapp.digikala.util.Constants.USER_LANGUAGE
import com.arysapp.digikala.util.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigiKalaTheme {
                navController = rememberNavController()
                ChangColorStatusBar(navController = navController)
                AppConfig()
                Log.e("language", USER_LANGUAGE)
                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
                val direction =
                    if (USER_LANGUAGE == PERSIAN_LANGUAGE)
                        LayoutDirection.Rtl
                    else
                        LayoutDirection.Ltr
                CompositionLocalProvider(value = LocalLayoutDirection provides direction) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController, onItemClick = {
                                    navController.navigate(it.route)
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    ) {
                        SetupNavGraph(navController = navController)
                        Log.e("route", navController.currentBackStackEntry?.destination?.route+"")
                    }
                }
            }

        }
    }
}

