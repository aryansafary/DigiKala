@file:Suppress("DEPRECATION")

package com.arysapp.digikala.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.arysapp.digikala.data.model.product_detail.Comment
import com.arysapp.digikala.ui.components.Loading3Dots
import com.arysapp.digikala.ui.components.OurLoading
import com.arysapp.digikala.ui.theme.*
import com.arysapp.digikala.util.Constants.PRODUCT_COMMENTS
import com.arysapp.digikala.util.DigitHelper
import com.arysapp.digikala.viewmodel.CommentViewModel
import com.arysapp.digikala.R
@Composable
fun AllProductCommentsScreen(
    viewModel: CommentViewModel = hiltViewModel(),
    navController: NavHostController,
    productId: String,
    commentsCount: String,
    pageName: String
) {
    val context = LocalContext.current

    val commentsCountText = if (commentsCount != "1")
        "${DigitHelper.digitByLocate(commentsCount)} ${stringResource(id = R.string.comment)}"
    else
        context.getString(R.string.all_comments)

    LaunchedEffect(true) {
        if (pageName == PRODUCT_COMMENTS)
            viewModel.getCommentList(productId)
        else {
            viewModel.getUserComments()

        }


    }

    val commentsList =
        if (pageName == PRODUCT_COMMENTS)
            viewModel.productCommentsList.collectAsLazyPagingItems()
        else
            viewModel.UserCommentsList.collectAsLazyPagingItems()




    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(24.dp)

            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = commentsCountText,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colors.searchBarBg)
        )

        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn(Modifier.fillMaxSize()) {

                //paging3
                items(
                    count = commentsList.itemCount,
                    key = commentsList.itemKey { comment -> comment._id },
                    contentType = commentsList.itemContentType { "Comments" }
                ) { index ->
                    CommentsItem(commentsList[index]!!)
                }

                /*items(commentsList.itemSnapshotList) { comment ->
                    CommentsItem(comment!!)
                }*/

                commentsList.apply {

                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                val config = LocalConfiguration.current
                                OurLoading(config.screenHeightDp.dp, true)
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(20.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Loading3Dots(isDark = true)
                                }
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            //todo error handling
                        }
                    }
                }
            }


        }

    }


}

@Composable
private fun CommentsItem(item: Comment) {

    val dateParts = item.updatedAt.substringBefore("T").split("-")
    val year = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val day = dateParts[2].toInt()

    val context = LocalContext.current

    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)
    var iconRotation = 0f
    when (item.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Oranges
            textSuggestion = context.getString(R.string.bad_comment)
            iconRotation = 180f
        }

        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colors.semiDarkText
            textSuggestion = context.getString(R.string.so_so_comment)
            iconRotation = 0f
        }

        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Green
            textSuggestion = context.getString(R.string.good_comment)
            iconRotation = 0f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .width(MaterialTheme.spacing.large)
                    .background(colorSuggestion),
                text = DigitHelper.digitByLocate(item.star.toString() + ".0"),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                text = DigitHelper.digitByLocate(DigitHelper.gregorianToJalali(year, month, day)),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6,
            )
            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                Modifier
                    .size(20.dp)
                    .padding(horizontal = MaterialTheme.spacing.small),
                tint = MaterialTheme.colors.grayAlpha
            )
            Text(
                text = item.userName,
                color = MaterialTheme.colors.grayAlpha,
                style = MaterialTheme.typography.h6
            )
        }

        Divider(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray,
        )


        Row(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.large
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconSuggestion),
                contentDescription = "",
                Modifier
                    .size(16.dp)
                    .rotate(iconRotation),
                tint = colorSuggestion
            )
            Text(
                text = textSuggestion,
                Modifier
                    .padding(start = MaterialTheme.spacing.extraSmall),
                maxLines = 1,
                style = MaterialTheme.typography.h6,
                color = colorSuggestion
            )
        }


        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large
                ),
            text = item.title,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5,
        )
        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.large,
                ),
            text = item.description,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5,
        )


    }
}