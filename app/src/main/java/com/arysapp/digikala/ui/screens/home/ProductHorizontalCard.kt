package com.arysapp.digikala.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.ui.theme.DarkCyan
import com.arysapp.digikala.ui.theme.darkText
import com.arysapp.digikala.ui.theme.extraBoldNumber
import com.arysapp.digikala.ui.theme.spacing
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductHorizontalCard(
    name: String,
    id: String,
    imageUrl: String
) {
    Row(
        modifier = Modifier
            .width(320.dp)
            .padding(bottom = MaterialTheme.spacing.extraSmall),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        GlideImage(
            model = imageUrl,
            contentDescription = "",
            modifier = Modifier
                .weight(.3f)
                .fillMaxHeight()
        )

        Text(
            text = id,
            style = MaterialTheme.typography.extraBoldNumber,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.DarkCyan,
            modifier = Modifier
                .weight(.1f)
                .padding(horizontal = MaterialTheme.spacing.small)
        )

        Column(
            modifier = Modifier
                .weight(.6f)
                .fillMaxHeight()
                .padding(vertical = MaterialTheme.spacing.small),
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
            )
        }

    }
}
