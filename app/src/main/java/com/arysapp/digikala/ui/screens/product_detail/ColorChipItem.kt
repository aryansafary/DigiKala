package com.arysapp.digikala.ui.screens.product_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arysapp.digikala.data.model.product_detail.ProductColor
import com.arysapp.digikala.ui.theme.CursorColor
import com.arysapp.digikala.ui.theme.roundedShape
import com.arysapp.digikala.ui.theme.spacing

@Composable
fun ColorChipItem(
    item: ProductColor,
    isSelected: Boolean,
    onSelectionChanged: (String) -> Unit = {},
) {

    Surface(
        modifier =
        if (isSelected)
            Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .border(width = 1.dp, MaterialTheme.colors.CursorColor, CircleShape)
        else
            Modifier.padding(MaterialTheme.spacing.extraSmall),
        elevation = 1.dp,
        shape = MaterialTheme.roundedShape.biggerMedium,
    ) {

        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(item.color)
                    }
                )
                .padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier
                    .size(20.dp)
                    .border(1.dp, Color.Gray, CircleShape),
                onDraw = {
                    drawCircle(Color(("ff" + item.code.removePrefix("#").lowercase()).toLong(16)))
                }
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            Text(
                text = item.color,
                style = MaterialTheme.typography.h6
            )

        }

    }


}