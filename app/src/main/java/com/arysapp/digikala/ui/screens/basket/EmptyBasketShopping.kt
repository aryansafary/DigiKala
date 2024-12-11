package com.arysapp.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.ui.theme.semiDarkText
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.R

@Composable
fun EmptyBasketShopping() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
       Image(
           painter = painterResource(id = R.drawable.empty_cart),
           contentDescription = "",
           contentScale = ContentScale.FillBounds,
           modifier = Modifier.height(200.dp)
       )

        Text(
            text = stringResource(R.string.cart_is_empty),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.semiDarkText,
        )
        Spacer(
            modifier = Modifier.height(MaterialTheme.spacing.medium)
        )

    }
}