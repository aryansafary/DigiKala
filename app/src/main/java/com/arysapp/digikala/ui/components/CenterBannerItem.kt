package com.arysapp.digikala.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.roundedShape
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.util.Constants
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CenterBannerItem(imageUrl: String) {

    Card(
        shape = MaterialTheme.roundedShape.semiMedium,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(MaterialTheme.spacing.medium)
    ) {

        GlideImage(
            model = imageUrl,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

}

@Composable
fun CenterBannerItem(
    painter: Painter,
    navController: NavHostController
) {

    Card(
        shape = MaterialTheme.roundedShape.semiMedium,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(MaterialTheme.spacing.medium)
            .clickable {
                navController.navigate(
                    route = Screen.WebView.route + "?url=${Constants.DIGI_CLUB}"
                )
            }
    ) {

        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

}