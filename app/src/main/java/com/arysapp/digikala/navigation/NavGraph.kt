package com.arysapp.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.arysapp.digikala.ui.screens.basket.BasketScreen
import com.arysapp.digikala.ui.screens.category.CategoryScreen
import com.arysapp.digikala.ui.screens.category.SubCategoryScreen
import com.arysapp.digikala.ui.screens.checkout.CheckoutScreen
import com.arysapp.digikala.ui.screens.checkout.ConfirmPurchaseScreen
import com.arysapp.digikala.ui.screens.home.HomeScreen
import com.arysapp.digikala.ui.screens.home.WebPageScreen
import com.arysapp.digikala.ui.screens.product_detail.*
import com.arysapp.digikala.ui.screens.profile.AddAddressScreen
import com.arysapp.digikala.ui.screens.profile.FavoriteListScreen
import com.arysapp.digikala.ui.screens.profile.ProfileScreen
import com.arysapp.digikala.ui.screens.profile.SettingScreen
import com.arysapp.digikala.ui.screens.profile.ShowAddressScreen
import com.arysapp.digikala.ui.screens.profile.UserAccountScreen
import com.arysapp.digikala.ui.screens.profile.orders.TabLayoutScreen
import com.arysapp.digikala.ui.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }

        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }

        composable(route = Screen.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("orderPrice") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("orderId")?.let { orderId ->
                it.arguments!!.getString("orderPrice")?.let { orderPrice ->
                    ConfirmPurchaseScreen(
                        navController = navController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }


        }




        composable(route = Screen.NewComment.route + "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("productName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("imageUrl") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("productName")?.let { productName ->
                    it.arguments!!.getString("imageUrl")?.let { imageUrl ->
                        NewCommentScreen(
                            navController = navController,
                            productId = productId,
                            productName = productName,
                            imageUrl = imageUrl
                        )
                    }
                }
            }


        }


        composable(route = Screen.ProductDetail.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                ProductDetailScreen(
                    navController = navController,
                    productId = productId
                )
            }

        }

        composable(route = Screen.AllComment.route + "/{productId}/{commentsCount}/{pageName}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("commentsCount") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("pageName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("commentsCount")?.let { commentsCount ->
                    it.arguments!!.getString("pageName")?.let { pageName ->
                        AllProductCommentsScreen(
                            navController = navController,
                            productId = productId,
                            commentsCount = commentsCount,
                            pageName = pageName
                        )
                    }
                }
            }

        }

        composable(route = Screen.ProductDescription.route + "/{description}",
            arguments = listOf(
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("description")?.let { description ->
                ProductDescriptionScreen(
                    navController = navController,
                    description = description
                )
            }

        }

        composable(route = Screen.ProductTechnicalFeatures.route + "/{jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductTechnicalFeaturesScreen(
                    navController = navController,
                    jsonString = jsonString
                )
            }

        }


        composable(route = Screen.ProductPriceChart.route + "?jsonString={jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductPriceChartScreen(
                    navController = navController,
                    jsonString = jsonString
                )
            }

        }


        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreen(navController = navController, url = url)
            }
        }


        composable(route = Screen.Setting.route) {
            SettingScreen(navController = navController)
        }

        composable(route = Screen.UserAccount.route) {
            UserAccountScreen(navController = navController)
        }

        composable(route = Screen.FavoriteList.route) {
            FavoriteListScreen(navController = navController)
        }

        composable(route = Screen.ShowAddressScreen.route + "/{isFromBasket}",
            arguments = listOf(
                navArgument("isFromBasket") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("isFromBasket")?.let { isFromBasket ->
                ShowAddressScreen(
                    navController = navController,
                    isFromBasket = isFromBasket.toInt()
                )
            }

        }

        composable(route = Screen.AddAddressScreen.route) {
            AddAddressScreen(navController = navController)
        }


        composable(route = Screen.TabLayoutScreen.route + "?orders={orders}",
            arguments = listOf(
                navArgument("orders") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("orders")?.let { orders ->
                TabLayoutScreen(
                    navController = navController,
                    orders = orders
                )
            }

        }


        composable(route = Screen.SubCategoryScreen.route + "/{categoryId}",
            arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("categoryId")?.let { categoryId ->
                SubCategoryScreen(
                    navController = navController,
                    categoryId = categoryId
                )
            }

        }


    }
}