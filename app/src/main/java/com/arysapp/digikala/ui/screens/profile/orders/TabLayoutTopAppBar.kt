package com.arysapp.digikala.ui.screens.profile.orders

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arysapp.digikala.R
import com.arysapp.digikala.ui.theme.font_standard
import com.arysapp.digikala.ui.theme.spacing

@Composable
fun TabLayoutTopAppBar(navController: NavHostController) {

    TopAppBar(backgroundColor = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = MaterialTheme.spacing.small)
            ,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "arrow back" ,
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .weight(0.1f)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Text(
                    text = stringResource(id = R.string.my_orders),
                    color = Color.DarkGray,
                    fontFamily = font_standard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(0.8f)
                )
                Icon(imageVector = Icons.Filled.Search,
                    contentDescription = "search" ,
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(end = MaterialTheme.spacing.small)
                        .clickable {
                            //TODO
                        }
                )
            }
        }
    }

}