package com.arysapp.digikala.ui.screens.product_detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arysapp.digikala.R
import com.arysapp.digikala.data.model.product_detail.NewComment
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.ui.components.OurLoading
import com.arysapp.digikala.ui.theme.*
import com.arysapp.digikala.util.Constants
import com.arysapp.digikala.viewmodel.CommentViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewCommentScreen(
    navController: NavController,
    productId: String,
    productName: String,
    imageUrl: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Header(navController, productName, imageUrl)
        CommentForm(navController, productId)
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Header(
    navController: NavController,
    productName: String,
    imageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.extraSmall,
                horizontal = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
            )
        }

        GlideImage(
            model = imageUrl,
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .clip(MaterialTheme.roundedShape.small)
                .size(50.dp)

        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.your_comment),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.darkText
            )
            Text(
                text = productName,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText
            )
        }

    }
    Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)


}


@Composable
fun CommentForm(
    navController: NavController,
    productId: String,
    viewModel: CommentViewModel = hiltViewModel()
) {


    var sliderValue by remember {
        mutableStateOf(0f)
    }


    val score = when (sliderValue.toInt()) {
        1 -> ""
        2 -> stringResource(id = R.string.very_bad)
        3 -> stringResource(id = R.string.bad)
        4 -> stringResource(id = R.string.normal)
        5 -> stringResource(id = R.string.good)
        6 -> stringResource(id = R.string.very_good)
        else -> ""
    }


    Text(
        text = score,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h2,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.darkText,
        textAlign = TextAlign.Center
    )


    Slider(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.large),
        value = sliderValue,
        onValueChange = { _sliderValue ->
            sliderValue = _sliderValue
        },
        onValueChangeFinished = {
            Log.d("3636", "sliderValue = $sliderValue")
        },
        valueRange = 1f..6f,
        steps = 4,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.amber,
            activeTrackColor = MaterialTheme.colors.amber,
            inactiveTrackColor = MaterialTheme.colors.grayCategory,
            activeTickColor = MaterialTheme.colors.amber,
            inactiveTickColor = MaterialTheme.colors.grayAlpha
        ),

        )

    Divider(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.semiMedium),
        color = MaterialTheme.colors.grayCategory,
        thickness = 1.dp,
    )

////////////////////////////////////////////////////////////////////////////////
    var commentTitle by remember { mutableStateOf("") }
    var commentBody by remember { mutableStateOf("") }

    val context = LocalContext.current


    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {

        viewModel.newCommentResult.collectLatest { newCommentResult ->
            when (newCommentResult) {
                is NetworkResult.Success -> {
                    if (newCommentResult.message.equals("کامنت با موفقیت ثبت شد")) { //todo edit backend
                        navController.popBackStack()
                    }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${newCommentResult.message}")
                }

                is NetworkResult.Loading -> {

                }

            }
        }

    }


    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.medium),
            text = stringResource(id = R.string.say_your_comment),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.darkText,
        )



        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall),
            text = stringResource(id = R.string.comment_title),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.darkText,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = commentTitle,
            onValueChange = { commentTitle = it },
            maxLines = 1,
            singleLine = true,
            shape = MaterialTheme.roundedShape.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.grayCategory,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )


        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.biggerMedium,
                    start = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.extraSmall,
                ),
            text = stringResource(id = R.string.comment_text),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.darkText,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = commentBody,
            onValueChange = { commentBody = it },
            shape = MaterialTheme.roundedShape.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.grayCategory,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            maxLines = 3,
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small,
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val checkedState = remember { mutableStateOf(false) }

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(MaterialTheme.colors.DarkCyan)
            )
            Text(
                text = stringResource(id = R.string.comment_anonymously),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.darkText,
            )
        }

        Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)


        if (loading) {
            OurLoading(height = 60.dp, isDark = true)
        } else {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.spacing.medium
                    ),
                onClick = {
                    loading = true

                    val userName = if (Constants.USER_NAME == "null") "کاربر بدون نام" else Constants.USER_NAME.replace("-","")

                    val newComment = NewComment(
                        token = Constants.USER_TOKEN,
                        productId = productId,
                        star = (sliderValue - 1).toInt(),
                        title = commentTitle,
                        description = commentBody,
                        userName = userName
                    )
                    if (newComment.title.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_title_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.star == 0) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_star_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.description.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_body_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        viewModel.setNewComment(newComment)
                    }

                }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.extraSmall),

                    text = stringResource(id = R.string.set_new_comment),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.semiDarkText
                )
            }
        }


    }

}