package com.arysapp.digikala.ui.screens.home
import com.arysapp.digikala.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.arysapp.digikala.navigation.Screens
import com.arysapp.digikala.ui.componets.RoundedIconBox
import com.arysapp.digikala.ui.theme.LocalSpacing
import com.arysapp.digikala.ui.theme.amber
import com.arysapp.digikala.ui.theme.grayCategory

@Composable
fun ShowCseSection(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalSpacing.current.semiMedium,
                vertical = LocalSpacing.current.biggerSmall
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.semiSmall)

        ) {
            RoundedIconBox(
                image = painterResource(R.drawable.digijet),
                title = stringResource(R.string.digikala_jet),
                onClick = onBoxClick(
                    navController = navController,
                    url = "https://www.digikalajet.com/user/address"
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.auction),
                title = stringResource(R.string.digi_style),
                onClick = onBoxClick(
                    navController = navController,
                    url = "https://www.digistyle.com/sale-landing/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=style&promo_name=style&promo_position=circle_badge"
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.digipay),
                title = stringResource(R.string.digi_pay),
                onClick = onBoxClick(
                    navController = navController,
                    url = "https://www.digikala.com/my-digipay/?promo_name=my-digipay&promo_position=circle_badge"
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.pindo),
                title = stringResource(R.string.pindo),
                bgColor = MaterialTheme.colors.amber,
                onClick = onBoxClick(
                    navController = navController,
                    url = "https://www.pindo.ir/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=pindo&promo_name=pindo&promo_position=circle_badge"

                ),
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.semiSmall)

        ) {
            RoundedIconBox(
                image = painterResource(R.drawable.shopping),
                title = stringResource(R.string.digi_shopping),
                onClick = onBoxClick(
                    navController = navController,
                    url = ""
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.giftcard),
                title = stringResource(R.string.gift_card),
                onClick = onBoxClick(
                    navController = navController,
                    url = "https://www.digikala.com/landing/gift-card-landing/?promo_name=gift_landing&promo_position=circle_badge"


                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.digiplus),
                title = stringResource(R.string.digi_plus),
                onClick = onBoxClick(
                    navController = navController,
                    url = "https://www.digikala.com/plus/landing/?promo_name=plus&promo_position=circle_badge"
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.more),
                title = stringResource(R.string.more),
                bgColor = MaterialTheme.colors.grayCategory,
                onClick = onBoxClick(
                    navController = navController,
                    url = ""
                ),
            )

        }

    }
}

@Composable
fun onBoxClick(navController: NavHostController, url: String): () -> Unit =
{ navController.navigate(route = Screens.WebPageScreen.route + "?url=${url}") }

