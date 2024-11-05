package com.arysapp.digikala

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.arysapp.digikala.ui.theme.DigiKalaTheme
import com.arysapp.digikala.util.Constants.PERSIAN_LANGUAGE
import com.arysapp.digikala.util.LocaleUtils

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DigiKalaTheme {
                navController = rememberNavController()
                LocaleUtils.setLocale(LocalContext.current,PERSIAN_LANGUAGE)
                CompositionLocalProvider(value = LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController =navController
                                , onItemClick = {
                                    navController.navigate(it.route)
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        ){
                    SetupNavGraph(navController = navController)
                }
            }
                }

        }
    }
}

