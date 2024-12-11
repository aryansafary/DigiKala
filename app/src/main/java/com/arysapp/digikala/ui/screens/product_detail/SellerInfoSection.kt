@file:Suppress("DEPRECATION")

package com.arysapp.digikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.ui.screens.basket.DetailRow
import com.arysapp.digikala.ui.theme.*
import com.arysapp.digikala.util.DigitHelper
import com.arysapp.digikala.R

@Composable
fun SellerInfoSection(productPrice:Long) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = MaterialTheme.spacing.small,
                horizontal = MaterialTheme.spacing.medium,
            )

    ) {


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Text(
            text = stringResource(id = R.string.seller),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Row(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_logo),
                modifier = Modifier
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {
                Text(
                    text = stringResource(id = R.string.digikala),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.darkText,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${DigitHelper.digitByLocate("99")}%" +
                                " رضایت خریداران | عملکرد ",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.semiDarkText,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
            }

        }


        Row(
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.semiMedium,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.guarantee),
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.small)
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.productWarranty),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.darkText,
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
            }

        }



        Row(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.biggerSmall)
        )
        {
            Column(
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.in_stock),
                    contentDescription = "",
                    modifier = Modifier
                        .size(16.dp),
                    tint = MaterialTheme.colors.DarkCyan
                )

                Divider(
                    Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .alpha(0.6f),
                    color = Color.LightGray
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp),
                    tint = MaterialTheme.colors.DarkCyan,
                )

                Divider(
                    Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .alpha(0.6f),
                    color = Color.LightGray
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp),
                    tint = MaterialTheme.colors.DarkCyan,
                )


            }

            Column(Modifier.padding(horizontal = MaterialTheme.spacing.biggerMedium)) {

                Text(
                    text = stringResource(id = R.string.in_digi_stock),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.semiDarkText,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.extraSmall),
                )

                DetailRow(
                    painterResource(id = R.drawable.k1),
                    stringResource(id = R.string.digikala_send),
                    color = MaterialTheme.colors.DigikalaLightRed,
                    fontStyle = MaterialTheme.typography.extraSmall
                )

                DetailRow(
                    painterResource(id = R.drawable.k2),
                    stringResource(id = R.string.fast_send),
                    color = MaterialTheme.colors.DigikalaLightGreen,
                    fontStyle = MaterialTheme.typography.extraSmall
                )

            }
        }


        Spacer(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )




        Row(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_score),
                modifier = Modifier
                    .size(20.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.digiclub_get_score),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.darkText,
                )

            }

        }


        Spacer(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )



        Row(
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.info),
                modifier = Modifier
                    .size(20.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = "${stringResource(id = R.string.manufacturer_price)} ${DigitHelper.digitByLocateAndSeparator(productPrice.toString())} ${
                        stringResource(
                            id = R.string.toman
                        )
                    }",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.unSelectedBottomBar,
                )
            }

        }


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))


        Row(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.better_price_suggestion),
                style = MaterialTheme.typography.extraSmall,
                color = MaterialTheme.colors.unSelectedBottomBar,
            )

            Image(
                painter = painterResource(id = R.drawable.mark),
                modifier = Modifier
                    .size(25.dp), contentDescription = ""
            )
        }

    }


}