package com.arysapp.digikala.ui.componets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.ui.theme.roundedCornerShape
import com.arysapp.digikala.ui.theme.spacing
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CenterBannerItem(imageUrl: String) {

    Card(
        shape = MaterialTheme.roundedCornerShape.semiMedium,
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

