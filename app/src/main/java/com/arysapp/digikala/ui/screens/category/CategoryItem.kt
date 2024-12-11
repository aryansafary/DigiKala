package com.arysapp.digikala.ui.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.arysapp.digikala.data.model.category.Sub
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.R
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.LightCyan

@Composable
fun CategoryItem(
    navController: NavHostController,
    categoryId: String,
    title: String,
    subList: List<Sub>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .clickable {
                    navController.navigate(Screen.SubCategoryScreen.withArgs(categoryId))
                },
            text = stringResource(id = R.string.see_all),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.LightCyan
        )
    }

    LazyRow(

    ) {
        items(subList) { list ->
            SubCategoryItem(navController, list)
        }
    }
}