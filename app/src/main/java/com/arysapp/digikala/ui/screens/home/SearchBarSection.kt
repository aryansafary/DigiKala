package com.arysapp.digikala.ui.screens.home

import androidx.compose.foundation.Image
import com.arysapp.digikala.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.ui.theme.LocalElevation
import com.arysapp.digikala.ui.theme.LocalShape
import com.arysapp.digikala.ui.theme.LocalSpacing
import com.arysapp.digikala.ui.theme.searchBarBg
import com.arysapp.digikala.util.Constants.PERSIAN_LANGUAGE
import com.arysapp.digikala.util.Constants.USER_LANGUAGE


@Composable
fun SearchBarSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.extraLarge)
            .background(Color.White),
        elevation =
        CardDefaults.cardElevation(
            defaultElevation = LocalElevation.current.extraSmall
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.small)
                .clip(LocalShape.current.biggerSmall)
                .background(MaterialTheme.colors.searchBarBg)
        ){
            SearchBarContent()
        }
    }
}
@Composable
private fun SearchBarContent() {
    Row (
    modifier = Modifier.fillMaxSize()
        .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Icon(
           modifier =
           Modifier.height(LocalSpacing.current.semiLarge),
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "searchIcon"
        )
        Text(
            text = stringResource(R.string.searchIn),
            modifier = Modifier.padding(start = 20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.h2,

        )
        Image(
            modifier =
            Modifier.
                width(80.dp).
            padding(start = LocalSpacing.current.extraSmall),
            painter =digikalaLogoChangeByLanguage() ,
            contentDescription = "appName"
        )

    }
}

@Composable
private fun digikalaLogoChangeByLanguage(): Painter
= if(USER_LANGUAGE == PERSIAN_LANGUAGE)
    painterResource(R.drawable.digikala_text_persian)
else
    painterResource(R.drawable.digikala_text_english)
