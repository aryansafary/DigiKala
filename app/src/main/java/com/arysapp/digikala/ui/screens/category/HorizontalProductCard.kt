@file:Suppress("DEPRECATION")

package com.arysapp.digikala.ui.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arysapp.digikala.R
import com.arysapp.digikala.data.model.home.StoreProduct
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.DarkCyan
import com.arysapp.digikala.ui.theme.DigikalaDarkRed
import com.arysapp.digikala.ui.theme.amber
import com.arysapp.digikala.ui.theme.darkText
import com.arysapp.digikala.ui.theme.semiDarkText
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.util.DigitHelper
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HorizontalProductCard(
    item: StoreProduct,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate(Screen.ProductDetail.withArgs(item._id))
            }
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            GlideImage(
                model = item.image,
                contentDescription = "",
                modifier = Modifier
                    .width(120.dp)
                    .height(100.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = item.name,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.darkText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
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
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.semiDarkText,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = DigitHelper.digitByLocate(item.star.toString()),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.semiDarkText,
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_star),
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(2.dp),
                            tint = MaterialTheme.colors.amber
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ){
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(24.dp)
                            .background(
                                color = MaterialTheme.colors.DigikalaDarkRed,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically),

                        ) {
                        Text(
                            text = "${DigitHelper.digitByLocate(item.discountPercent.toString())}%",
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,

                            )
                    }

                    Column() {
                        Row() {
                            Text(
                                text = DigitHelper.digitBySeparator(
                                    DigitHelper.digitByLocate(
                                        DigitHelper.applyDiscount(item.price, item.discountPercent).toString()
                                    )
                                ),
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Image(
                                painter = painterResource(id = R.drawable.toman),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(MaterialTheme.spacing.semiLarge)
                                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                            )
                        }
                        Text(
                            text = DigitHelper.digitBySeparator(
                                DigitHelper.digitByLocate(
                                    item.price.toString()
                                )
                            ),
                            color = Color.LightGray,
                            style = MaterialTheme.typography.body2,
                            textDecoration = TextDecoration.LineThrough,
                        )

                    }
                }




            }
        }


        Divider(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical =MaterialTheme.spacing.small
                )
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray,
        )

    }


}