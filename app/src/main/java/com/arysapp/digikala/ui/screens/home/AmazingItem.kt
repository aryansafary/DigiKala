package com.arysapp.digikala.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arysapp.digikala.data.model.home.AmazingItem
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.*
import com.arysapp.digikala.util.Constants
import com.arysapp.digikala.util.DigitHelper.applyDiscount
import com.arysapp.digikala.util.DigitHelper.digitByLocateAndSeparator
import com.arysapp.digikala.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AmazingItem(
    item: AmazingItem,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .width(170.dp)
            .padding(
                vertical = MaterialTheme.spacing.semiLarge,
                horizontal = MaterialTheme.spacing.semiSmall
            )
            .clickable{
                navController.navigate(Screen.ProductDetail.withArgs(item._id))
            }
        ,
        shape = MaterialTheme.roundedShape.small,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.extraSmall)
            ) {

                Text(
                    text = stringResource(id = R.string.amazing_for_app),
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.small),
                    style = MaterialTheme.typography.extraSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.DigikalaLightRedText,
                )

                Spacer(modifier = Modifier.height(10.dp))

                GlideImage(
                    model = item.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    contentScale = ContentScale.FillBounds
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small)
            ) {

                Text(
                    text = item.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(48.dp)
                        .padding(horizontal = MaterialTheme.spacing.small),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.darkText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.in_stock),
                        contentDescription = "",
                        modifier = Modifier
                            .size(22.dp)
                            .padding(2.dp),
                        tint = MaterialTheme.colors.DarkCyan
                    )
                    Text(
                        text = item.seller,
                        style = MaterialTheme.typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.semiDarkText,
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.small),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(24.dp)
                            .background(
                                color = MaterialTheme.colors.DigikalaDarkRed,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "${digitByLocateAndSeparator(item.discountPercent.toString())}%",
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                        )
                    }


                    Column {

                        Row {
                            Text(
                                text = digitByLocateAndSeparator(applyDiscount(item.price , item.discountPercent).toString()),
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.SemiBold,
                            )

                            Icon(
                                painter = currencyLogoChangeByLanguage(),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(MaterialTheme.spacing.semiLarge)
                                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                            )

                        }

                        Text(
                            text = digitByLocateAndSeparator(item.price.toString()),
                            color = Color.LightGray,
                            style = MaterialTheme.typography.body2,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                }


            }


        }


    }

}

@Composable
private fun currencyLogoChangeByLanguage(): Painter {
    return if (Constants.USER_LANGUAGE == Constants.ENGLISH_LANGUAGE) {
        painterResource(id = R.drawable.dollar)
    } else {
        painterResource(id = R.drawable.toman)
    }
}