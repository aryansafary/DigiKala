package com.arysapp.digikala.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arysapp.digikala.R


@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick : (BottomNavigationItem)->Unit,
    modifier: Modifier
){
val items = listOf(
    BottomNavigationItem(
        name = stringResource(id = R.string.Home),
        route = Screens.HomeScreen.route,
        selectedIcon = painterResource(id =R.drawable.home_fill ),
        deselectedIcon = painterResource(id =R.drawable.home_outline),
    ),
    BottomNavigationItem(
        name = stringResource(id = R.string.Category),
        route = Screens.CategoryScreen.route,
        selectedIcon = painterResource(id =R.drawable.category_fill ),
        deselectedIcon = painterResource(id =R.drawable.category_outline),
    ),
    BottomNavigationItem(
        name =  stringResource(id = R.string.Basket),
        route = Screens.BasketScreen.route,
        selectedIcon = painterResource(id = R.drawable.cart_fill ),
        deselectedIcon = painterResource(id =R.drawable.cart_outline),
    ),
    BottomNavigationItem(
        name =  stringResource(id = R.string.MyDigiKala),
        route = Screens.ProfileScreen.route,
        selectedIcon = painterResource(id =R.drawable.user_fill ),
        deselectedIcon = painterResource(id =R.drawable.user_outline),
    ),
)
val backStackEntry = navController.currentBackStackEntryAsState()
val showBottomNavigationBar = backStackEntry.value?.destination?.route in items.map { it.route }
if(showBottomNavigationBar){
    BottomAppBar(
        modifier = modifier,
        containerColor = Color.White,
        contentColor = Color.Gray
    ) {
items.forEachIndexed{ _, item ->
val selected = item.route == backStackEntry.value?.destination?.route
NavigationBarItem(
    selected =selected ,
    onClick ={ onItemClick(item) },
icon = {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
    if(selected){
    Icon(
        painter = item.selectedIcon,
        contentDescription =item.name,
        modifier = Modifier.height(24.dp))
    }else{
        Icon(
            painter = item.deselectedIcon,
            contentDescription =item.name,
            modifier = Modifier.height(24.dp))
    }
        Text(
            text = item.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 5.dp)
            )
    }
},
    colors = NavigationBarItemDefaults.colors(
        selectedIconColor = Color.Black,
        unselectedIconColor = Color.Gray,
        selectedTextColor = Color.Black,
        unselectedTextColor = Color.Gray,
        indicatorColor = Color.Transparent
    )

    )
}
    }
}
}