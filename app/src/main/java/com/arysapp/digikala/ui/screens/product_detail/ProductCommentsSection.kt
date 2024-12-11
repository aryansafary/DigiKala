@file:Suppress("DEPRECATION")

package com.arysapp.digikala.ui.screens.product_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arysapp.digikala.data.model.product_detail.Comment
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.LightCyan
import com.arysapp.digikala.ui.theme.darkText
import com.arysapp.digikala.util.Constants.PRODUCT_COMMENTS
import com.arysapp.digikala.util.DigitHelper.digitByLocate
import com.arysapp.digikala.R

@Composable
fun ProductCommentsSection(
    navController: NavHostController,
    productId: String,
    comments: List<Comment>,
    commentCount: String
) {


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.semiLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.user_comments),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = "${digitByLocate(commentCount.toString())} " + stringResource(R.string.comment),
                color = MaterialTheme.colors.LightCyan,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    navController.navigate(
                        Screen.AllComment.withArgs(
                            productId,
                            commentCount,
                            PRODUCT_COMMENTS
                        )
                    )
                }
            )
        }
    }


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium),
    ) {
        items(comments) { comment ->
            TextCommentCard(comment)
        }
        item {
            CommentShowMoreItem(navController, productId, commentCount)
        }
    }

}