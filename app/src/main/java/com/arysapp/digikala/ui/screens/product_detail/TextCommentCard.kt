package com.arysapp.digikala.ui.screens.product_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.data.model.product_detail.Comment
import com.arysapp.digikala.ui.theme.*
import com.arysapp.digikala.util.DigitHelper.digitByLocate
import com.arysapp.digikala.util.DigitHelper.gregorianToJalali
import com.arysapp.digikala.R

@Composable
fun TextCommentCard(
    item: Comment
) {


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




    Card(
        modifier = Modifier
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.medium,
            )
            .width(260.dp)
            .height(210.dp),
        shape = MaterialTheme.roundedShape.small,
        elevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = item.title,
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h5,
            )

            Row(
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small),
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
                text = item.description,
                Modifier.weight(1f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = digitByLocate(gregorianToJalali(year, month, day)),
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

        }
    }
}