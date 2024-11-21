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
import com.arysapp.digikala.util.Constants.AUCTION_URL
import com.arysapp.digikala.util.Constants.DIGIJET_URL
import com.arysapp.digikala.util.Constants.DIGIPAY_URL
import com.arysapp.digikala.util.Constants.DIGIPLUS_URL
import com.arysapp.digikala.util.Constants.GIFT_CARD_URL
import com.arysapp.digikala.util.Constants.MORE_URL
import com.arysapp.digikala.util.Constants.PINDO_URL
import com.arysapp.digikala.util.Constants.SHOPPING_URL

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
                    url =DIGIJET_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.auction),
                title = stringResource(R.string.digi_style),
                onClick = onBoxClick(
                    navController = navController,
                    url = AUCTION_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.digipay),
                title = stringResource(R.string.digi_pay),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGIPAY_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.pindo),
                title = stringResource(R.string.pindo),
                bgColor = MaterialTheme.colors.amber,
                onClick = onBoxClick(
                    navController = navController,
                    url = PINDO_URL

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
                    url = SHOPPING_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.giftcard),
                title = stringResource(R.string.gift_card),
                onClick = onBoxClick(
                    navController = navController,
                    url = GIFT_CARD_URL


                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.digiplus),
                title = stringResource(R.string.digi_plus),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGIPLUS_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(R.drawable.more),
                title = stringResource(R.string.more),
                bgColor = MaterialTheme.colors.grayCategory,
                onClick = onBoxClick(
                    navController = navController,
                    url = MORE_URL
                ),
            )

        }

    }
}

@Composable
fun onBoxClick(navController: NavHostController, url: String): () -> Unit =
{ navController.navigate(route = Screens.WebPageScreen.route + "?url=${url}") }

