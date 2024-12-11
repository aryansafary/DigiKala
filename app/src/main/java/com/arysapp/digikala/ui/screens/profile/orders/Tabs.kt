package com.arysapp.digikala.ui.screens.profile.orders

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.arysapp.digikala.data.model.checkout.OrderFullDetail
import com.arysapp.digikala.data.model.prfile.TabItem
import com.arysapp.digikala.ui.theme.darkText
import com.arysapp.digikala.ui.theme.digikalaRed
import com.arysapp.digikala.ui.theme.font_standard
import kotlinx.coroutines.launch
import com.arysapp.digikala.R
import com.arysapp.digikala.util.DigitHelper

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState, orders: List<OrderFullDetail>) {
    val coroutineScope = rememberCoroutineScope()
    val list = listOf<TabItem>(
        TabItem(
            "${stringResource(id = R.string.waiting_for_purchase)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="0" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "0" }) },

        TabItem(
            "${stringResource(id = R.string.processing_orders)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="1" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "1" }) },


        TabItem(
            "${stringResource(id = R.string.delivered_orders)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="2" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "2" }) },


        TabItem(
            "${stringResource(id = R.string.canceled_orders)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="3" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "3" }) },


        TabItem(
            "${stringResource(id = R.string.returned_orders)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="4" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "4" }) },
    )

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = MaterialTheme.colors.darkText,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = MaterialTheme.colors.digikalaRed
            )
        }
    ) {

        list.forEachIndexed { index, tabItem ->
            Tab(
                text = {
                    Text(
                        text = list[index].title,
                        color = if (pagerState.currentPage == index) Color.DarkGray else Color.LightGray,
                        fontFamily = font_standard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier.wrapContentWidth()
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }

    }


}