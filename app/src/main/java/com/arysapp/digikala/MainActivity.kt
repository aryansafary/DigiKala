package com.arysapp.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arysapp.digikala.ui.theme.DigikalaTheme
import dagger.hilt.android.AndroidEntryPoint
import com.arysapp.digikala.navigation.BottomNavigationBar
import com.arysapp.digikala.navigation.SetupNavGraph
import com.arysapp.digikala.ui.components.AppConfig
import com.arysapp.digikala.ui.components.ChangeStatusBarColor
import com.arysapp.digikala.util.Constants.ENGLISH_LANGUAGE
import com.arysapp.digikala.util.Constants.USER_LANGUAGE
import com.arysapp.digikala.util.Constants.afterPurchaseUrl
import com.arysapp.digikala.util.Constants.isFromPurchase
import com.arysapp.digikala.util.LocaleUtils

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme() {
                navController = rememberNavController()
                ChangeStatusBarColor(navController = navController)


                AppConfig()

                Log.e("3636", USER_LANGUAGE)

                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

                val direction = if (USER_LANGUAGE == ENGLISH_LANGUAGE) {
                    androidx.compose.ui.unit.LayoutDirection.Ltr
                } else {
                    androidx.compose.ui.unit.LayoutDirection.Rtl
                }
                CompositionLocalProvider(LocalLayoutDirection provides direction) {

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                onItemClick = {
                                   navController.navigate(it.route)
                                })
                        }
                    ) {
                        SetupNavGraph(navController = navController)
                    }

                }


            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (
            intent != null &&
            intent.data != null &&
            intent.data?.scheme == "AryApp" &&
            intent.data?.host == "digikala"
        ) {
            val url = intent.data.toString()
            Log.e("LogMessage", "URL: $url")
            isFromPurchase = true
            afterPurchaseUrl = url
        }
    }


}
