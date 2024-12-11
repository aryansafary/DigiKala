package com.arysapp.digikala.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")
    data object Home : Screen("home_screen")
    data object Category : Screen("category_screen")
    data object Basket : Screen("basket_screen")
    data object Profile : Screen("profile_screen")
    data object Checkout : Screen("checkout_screen")
    data object ConfirmPurchase : Screen("confirm_purchase_screen")
    data object ProductDetail : Screen("product_detail_screen")
    data object ProductDescription : Screen("product_description_screen")
    data object ProductTechnicalFeatures : Screen("product_technical_features")
    data object ProductPriceChart : Screen("product_price_chart")
    data object NewComment : Screen("new_comment_screen")
    data object AllComment : Screen("all_comment_screen")
    data object WebView : Screen("webView_screen")
    data object Setting : Screen("setting_screen")
    data object UserAccount : Screen("User_Account_Screen")
    data object FavoriteList : Screen("favorite_list_screen")
    data object ShowAddressScreen : Screen("show_address_screen")
    data object AddAddressScreen : Screen("add_address_screen")
    data object TabLayoutScreen: Screen("tab_layout_screen")
    data object SubCategoryScreen : Screen("sub_category_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}