package com.arysapp.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import com.arysapp.digikala.R
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.arysapp.digikala.ui.theme.digikalaRed
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.viewmodel.BasketViewModel

@Composable
fun BasketScreen(navController: NavHostController) {

    Basket(navController)

}

@Composable
fun Basket(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val currentCartItemsCount by viewModel.currentCartItemsCount.collectAsState(0)
    val nextCartItemsCount by viewModel.nextCartItemsCount.collectAsState(0)


    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val tabTitles = listOf(
        stringResource(id = R.string.cart),
        stringResource(id = R.string.next_cart_list)
    )

    Column {

        TabRow(
            modifier = Modifier
                .background(color = Color.White)
                .padding(horizontal = MaterialTheme.spacing.medium),
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colors.digikalaRed,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(Color.Red)

                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = (selectedTabIndex == index),
                    onClick = {
                        selectedTabIndex = index
                    },
                   // selectedContentColor = MaterialTheme.colors.digikalaRed,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold,
                            )

                            val cartCounter = if (index == 0) {
                                currentCartItemsCount
                            } else {
                                nextCartItemsCount
                            }
                            if (cartCounter > 0) {
                                Spacer(modifier = Modifier.width(10.dp))
                                SetBadgeToTab(
                                    selectedTabIndex = selectedTabIndex,
                                    index = index,
                                    cartCounter = cartCounter
                                )
                            }

                        }
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> ShoppingCart(navController)
            1 -> NextShoppingList(navController)
        }

    }

}

