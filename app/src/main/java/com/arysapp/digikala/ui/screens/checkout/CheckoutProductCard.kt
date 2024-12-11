@file:Suppress("DEPRECATION")

package com.arysapp.digikala.ui.screens.checkout
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.data.model.basket.CartItem
import com.arysapp.digikala.ui.theme.extraSmall
import com.arysapp.digikala.ui.theme.roundedShape
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.util.DigitHelper
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CheckoutProductCard(
    item: CartItem
) {
    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .size(75.dp)
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
        ) {
            GlideImage(
                model = item.image,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }

        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(MaterialTheme.roundedShape.extraSmall),
            contentAlignment = Alignment.BottomEnd
        ){
            Text(
                text = DigitHelper.digitByLocate(item.count.toString()),
                style = MaterialTheme.typography.extraSmall
            )
        }

    }

    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .height(70.dp)
            .alpha(0.4f)
            .width(1.dp)

    )
}