package com.arysapp.digikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.ui.theme.Oranges
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.util.DigitHelper.digitByLocate
import com.arysapp.digikala.R
@Composable
fun DeliveryTimeBottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = true,
                    onClick = {}
                )
                Text(
                    text = stringResource(id = R.string.pishtaz_post),
                    style = MaterialTheme.typography.h6
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.digi_plus_icon),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = digitByLocate(stringResource(id = R.string.delivery_delay)),
                    color = MaterialTheme.colors.Oranges,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                )
            }
        }
    }
}