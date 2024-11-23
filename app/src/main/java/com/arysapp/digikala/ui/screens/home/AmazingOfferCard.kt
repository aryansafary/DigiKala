package com.arysapp.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.R


@Composable
fun AmazingOfferCard(topImageResId: Int, bottomImageResId: Int) {

    Column(
        modifier = Modifier
            .width(160.dp)
            .height(380.dp)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.extraSmall
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = topImageResId),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(id = bottomImageResId),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.h6,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "see_all",
                tint = Color.White
            )
        }
    }

}
