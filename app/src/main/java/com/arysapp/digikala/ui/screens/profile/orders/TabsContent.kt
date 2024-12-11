package com.arysapp.digikala.ui.screens.profile.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.arysapp.digikala.R
import com.arysapp.digikala.data.model.checkout.OrderFullDetail
import com.arysapp.digikala.ui.theme.darkText
import com.arysapp.digikala.ui.theme.roundedShape
import com.arysapp.digikala.ui.theme.searchBarBg
import com.arysapp.digikala.ui.theme.semiDarkText
import com.arysapp.digikala.ui.theme.settingArrow
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.util.DigitHelper
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState, orders: List<OrderFullDetail>) {

    HorizontalPager(state = pagerState, count = 5) { page ->
        when (page) {
            0 -> TabContentScreen(orders.filter { it.orderStatus == "0" })
            1 -> TabContentScreen(orders.filter { it.orderStatus == "1" })
            2 -> TabContentScreen(orders.filter { it.orderStatus == "2" })
            3 -> TabContentScreen(orders.filter { it.orderStatus == "3" })
            4 -> TabContentScreen(orders.filter { it.orderStatus == "4" })
        }
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TabContentScreen(orders: List<OrderFullDetail>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.searchBarBg),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (orders.size > 0) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(orders) { item ->
                    Card(
                        shape = MaterialTheme.roundedShape.small,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                MaterialTheme.spacing.extraSmall
                            )
                    ) {
                        Column {
                            Row(
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.small),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = "${stringResource(R.string.order_code)} ${
                                       item._id.takeLast(10)
                                    }",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.h5,
                                    color = MaterialTheme.colors.darkText,
                                )

                                Row() {
                                    Text(
                                        text = DigitHelper.digitBySeparator(
                                            DigitHelper.digitByLocate(
                                                item.orderTotalPrice.toString()
                                            )
                                        )
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.toman),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(MaterialTheme.spacing.semiLarge)
                                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                                    )
                                }
                            }


                            val dateParts = item.updatedAt.substringBefore("T").split("-")
                            val year = dateParts[0].toInt()
                            val month = dateParts[1].toInt()
                            val day = dateParts[2].toInt()

                            Text(
                                text = DigitHelper.digitByLocate(
                                    DigitHelper.gregorianToJalali(
                                        year,
                                        month,
                                        day
                                    )
                                ),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.semiDarkText,
                                modifier = Modifier.padding(
                                    horizontal = MaterialTheme.spacing.small
                                )
                            )

                            Row(
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.small),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){

                                Row(
                                    Modifier.padding(vertical = MaterialTheme.spacing.small)
                                ){
                                    for (product in item.orderProducts.takeLast(4)){
                                        GlideImage(
                                            model = product.image,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(50.dp)
                                            )
                                    }
                                }

                                Icon(
                                    Icons.Outlined.KeyboardArrowLeft,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(top = MaterialTheme.spacing.medium)
                                        .size(26.dp),
                                    tint = MaterialTheme.colors.settingArrow
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

