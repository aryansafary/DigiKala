package com.arysapp.digikala.ui.screens.product_detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.arysapp.digikala.R
import com.arysapp.digikala.data.model.prfile.FavItem
import com.arysapp.digikala.data.model.product_detail.ProductDetail
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.darkText
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun ProductTopAppBar(navController: NavHostController, product: ProductDetail) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(0.6f),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.exit),
                    contentDescription = "",
                    modifier = Modifier.size(17.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }
        }

        Row(
            modifier = Modifier.weight(0.4f),
        ) {

            IconButton(onClick = {
                navController.navigate(Screen.Basket.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

            DisplayFavoriteToggleButton(
                FavItem(
                    product._id ?: "",
                    product.discountPercent ?: 0,
                    product.imageSlider?.get(0)?.image ?: "",
                    product.name ?: "",
                    product.price ?: 0,
                    product.seller ?: "",
                    product.star ?: 0.0
                )
            )


            var expanded by remember { mutableStateOf(false) }
            IconButton(
                onClick = {
                    expanded = true
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_dots),
                    contentDescription = "",
                    modifier = Modifier.size(27.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(MaterialTheme.colors.surface)
            ) {
                DropdownMenuItem(
                    onClick = {
                        val priceListString = Gson().toJson(product.priceList)
                        expanded = false
                        navController.navigate(
                            Screen.ProductPriceChart.route + "?jsonString=${priceListString}"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.chart),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp),
                            tint = MaterialTheme.colors.darkText
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = stringResource(R.string.price_chart),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText,
                        )
                    }
                }

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        shareToSocialMedia(
                            context,
                            product.name!!,
                            digitByLocateAndSeparator(product.price!!.toString()),
                            "https://truelearn.ir/"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp),
                            tint = MaterialTheme.colors.darkText
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = stringResource(R.string.share_product),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText,
                        )
                    }
                }
            }

        }


    }
}

private fun shareToSocialMedia(
    context: Context,
    productName: String,
    productPrice: String,
    url: String
) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "$productName با قیمت باورنکردنی $productPrice تومان فقط در فروشگاه زیر \n $url"
    )

    context.startActivity(Intent.createChooser(shareIntent , "share to..."))
}

