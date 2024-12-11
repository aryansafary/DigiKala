package com.arysapp.digikala.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import com.arysapp.digikala.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.arysapp.digikala.data.model.checkout.OrderFullDetail
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.components.CenterBannerItem
import com.arysapp.digikala.ui.theme.*
import com.arysapp.digikala.util.Constants
import com.arysapp.digikala.util.Constants.USER_COMMENTS
import com.arysapp.digikala.util.DigitHelper
import com.arysapp.digikala.util.DigitHelper.digitByLocate
import com.arysapp.digikala.viewmodel.DataStoreViewModel
import com.arysapp.digikala.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        profileViewModel.getUserOrders()
    }

    var orderItemsList by remember {
        mutableStateOf<List<OrderFullDetail>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val orderItemsResult by profileViewModel.orderItems.collectAsState()
    when (orderItemsResult) {
        is NetworkResult.Success -> {
            orderItemsList = orderItemsResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "OrderItemsResultSection error : ${orderItemsResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    val userToken = dataStore.getUserToken()
    if (!userToken.isNullOrBlank() && userToken != "null") {
        Profile(navController, orderItemsList)
    } else {
        when (profileViewModel.screenState) {
            ProfileScreenState.LOGIN_STATE -> {
                LoginScreen()
            }

            ProfileScreenState.PROFILE_STATE -> {
                Profile(navController, orderItemsList)
            }

            ProfileScreenState.REGISTER_STATE -> {
                RegisterScreen()
            }
        }
    }


}

@Composable
fun Profile(navController: NavHostController, orders: List<OrderFullDetail>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 60.dp),
    ) {
        item { ProfileTopBarSection(navController) }
        item { ProfileHeaderSection(navController) }
        item { ProfileMiddleSection(navController) }
        item { MyOrdersSection(navController, orders) }
        item {
            CenterBannerItem(
                painter = painterResource(id = R.drawable.digiclub1),
                navController
            )
        }
        item { ProfileMenuSection(navController) }
        item {
            CenterBannerItem(
                painter = painterResource(id = R.drawable.digiclub2),
                navController
            )
        }
    }
}


@Composable
private fun ProfileMenuSection(navController: NavHostController) {

    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_plus_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.digi_plus),
        isHaveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${Constants.DIGIPLUS_URL}"
        )

    }
    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_fav_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.fav_list),
        isHaveDivider = true
    ) {
        navController.navigate(Screen.FavoriteList.route)
    }

    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_comments_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.my_comments),
        isHaveDivider = true
    ) {
        navController.navigate(
            Screen.AllComment.withArgs(
                "1",
                "1",
                USER_COMMENTS
            )
        )
    }
    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_adresses_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.addresses),
        isHaveDivider = true
    ) {
        navController.navigate(Screen.ShowAddressScreen.withArgs(-1))
    }

    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_profile_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.profile_data),
        isHaveDivider = false
    ) {
        navController.navigate(Screen.UserAccount.route)
    }


}


@Composable
private fun MyOrdersSection(navController: NavHostController, orders: List<OrderFullDetail>) {

    val waitForPurchaseOrders = orders.filter { it.orderStatus == "0" }
    val purchasedOrders = orders.filter { it.orderStatus == "1" }
    val deliveredOrders = orders.filter { it.orderStatus == "2" }
    val canceledOrders = orders.filter { it.orderStatus == "3" }
    val returnedOrders = orders.filter { it.orderStatus == "4" }


    Text(
        modifier = Modifier.padding(MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h3,
        fontWeight = FontWeight.Bold,
        text = stringResource(id = R.string.my_orders),
    )

    LazyRow(
        modifier = Modifier
            .clickable {
                val ordersString = Gson().toJson(orders)
                navController.navigate(
                    Screen.TabLayoutScreen.route + "?orders=${ordersString}"
                )
            }
    ) {
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.unpaid),
                count = waitForPurchaseOrders.size,
                painter = painterResource(id = R.drawable.digi_unpaid)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.processing),
                count = purchasedOrders.size,
                painter = painterResource(id = R.drawable.digi_processing)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.my_orders),
                count = deliveredOrders.size,
                painter = painterResource(id = R.drawable.digi_delivered)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.canceled),
                count = canceledOrders.size,
                painter = painterResource(id = R.drawable.digi_cancel)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.returned),
                count = returnedOrders.size,
                painter = painterResource(id = R.drawable.digi_returned)
            )
        }
    }
}


@Composable
private fun MyOrdersItem(
    text: String,
    count: Int,
    painter: Painter
) {
    Row(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.biggerMedium)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            ){

                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )

                Card(
                    Modifier.align(Alignment.BottomEnd),
                    shape = MaterialTheme.roundedShape.extraSmall,
                    border = BorderStroke(1.dp, Color.White)
                ){
                    Text(
                        text = DigitHelper.digitByLocateAndSeparator(count.toString()),
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.digikalaRed)
                            .height(20.dp)
                            .padding(horizontal = MaterialTheme.spacing.semiSmall),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.extraSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }

            }

            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText,
                text = text
            )

        }
        Divider(
            modifier = Modifier
                .width(1.dp)
                .height(90.dp)
                .alpha(0.4f),
            color = Color.LightGray,
        )
    }
}

@Composable
private fun ProfileTopBarSection(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            navController.navigate(Screen.Setting.route)
        }) {
            Icon(
                painter = painterResource(
                    id = R.drawable.digi_settings
                ), contentDescription = "",
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small
                    )
                    .size(MaterialTheme.spacing.semiLarge),
                tint = MaterialTheme.colors.selectedBottomBar
            )
        }

        IconButton(onClick = {
            navController.navigate(Screen.Home.route)
        }) {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small),
                tint = MaterialTheme.colors.selectedBottomBar
            )
        }
    }
}

@Composable
private fun ProfileHeaderSection(navController: NavController) {

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

    if (Constants.USER_NAME != "null") {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = Constants.USER_NAME.replace("-", ""),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h5
        )
    } else {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.UserAccount.route) },
            text = stringResource(id = R.string.completion_of_user_information),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.DarkCyan,
            style = MaterialTheme.typography.h5
        )
    }



    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.semiDarkText,
        text = digitByLocate(Constants.USER_PHONE)
    )

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Row(
            modifier = Modifier
                .weight(0.49f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(42.dp)
            )
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.semiMedium)
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.extraSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.semiDarkText,
                        text = "${digitByLocate("975")} "
                    )
                    Text(
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.semiDarkText,
                        text = stringResource(R.string.score)
                    )
                }

                Text(
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.darkText,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(id = R.string.digikala_score)
                )
            }


        }

        Divider(
            modifier = Modifier
                .width(2.dp)
                .height(60.dp)
                .alpha(0.2f),
            color = Color.LightGray,
        )

        Column(
            modifier = Modifier
                .clickable {
                    navController.navigate(
                        route = Screen.WebView.route + "?url=${Constants.DIGI_WALLET}"
                    )
                }
                .weight(0.49f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_wallet),
                contentDescription = "",
                modifier = Modifier
                    .size(34.dp)
            )

            Text(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
                text = stringResource(id = R.string.digikala_wallet_active)
            )

        }

    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))
}


@Composable
private fun ProfileMiddleSection(navController: NavHostController) {

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.biggerMedium)
    ) {
        Column(
            Modifier.clickable {
                navController.navigate(Screen.UserAccount.route)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(id = R.drawable.digi_user),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText,
                text = stringResource(R.string.auth)
            )
        }

        Column(
            Modifier.clickable {
                navController.navigate(
                    route = Screen.WebView.route + "?url=${Constants.DIGI_CLUB}"
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(id = R.drawable.digi_club),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText,
                text = stringResource(R.string.club)
            )
        }


        Column(
            Modifier.clickable {
                navController.navigate(
                    route = Screen.WebView.route + "?url=${Constants.TURLEARN_CONTACT_US}"
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(id = R.drawable.digi_contact_us),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText,
                text = stringResource(R.string.contact_us)
            )
        }

    }


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )


}