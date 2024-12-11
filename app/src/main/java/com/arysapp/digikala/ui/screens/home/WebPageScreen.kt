package com.arysapp.digikala.ui.screens.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.arysapp.digikala.ui.components.OurLoading

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageScreen(
    navController: NavController,
    url: String
) {
    var isLoading by remember { mutableStateOf(true) }

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            webViewClient = object : WebViewClient(){

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    isLoading = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    isLoading = false
                }
            }
            settings.javaScriptEnabled = true
            settings.userAgentString = System.getProperty("http.agent")
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })


    if (isLoading){
        val config = LocalConfiguration.current
        OurLoading(config.screenHeightDp.dp, true)
    }

}