package com.arysapp.digikala.ui.screens.product_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arysapp.digikala.ui.theme.DarkCyan
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.darkText
import com.arysapp.digikala.util.Constants.PRODUCT_COMMENTS
import com.arysapp.digikala.R

@Composable
fun CommentShowMoreItem(
    navController: NavHostController,
    productId: String,
    commentCount: String,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(180.dp)
            .height(240.dp)
            .padding(vertical = MaterialTheme.spacing.medium)
            .clickable {
                navController.navigate(
                    Screen.AllComment.withArgs(
                        productId,
                        commentCount,
                        PRODUCT_COMMENTS
                    )
                )
            }

    ) {
        Icon(
            painter = painterResource(id = R.drawable.show_more),
            contentDescription = "",
            tint = MaterialTheme.colors.DarkCyan,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            text = stringResource(R.string.see_all),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
            textAlign = TextAlign.Center,
        )

    }
}