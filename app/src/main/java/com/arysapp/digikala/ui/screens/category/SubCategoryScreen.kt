package com.arysapp.digikala.ui.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.arysapp.digikala.ui.screens.home.SearchBarSection
import com.arysapp.digikala.viewmodel.CategoryViewModel

@Composable
fun SubCategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navController: NavHostController,
    categoryId: String
){



    LaunchedEffect(true) {
        viewModel.getProductByCategory(categoryId)
    }

    val productList = viewModel.productByCategoryList.collectAsLazyPagingItems()


    Column {
        SearchBarSection()
        LazyColumn(Modifier.fillMaxSize()){
            //paging3
            items(
                count = productList.itemCount,
                key = productList.itemKey{product -> product._id},
                contentType = productList.itemContentType{"product"}
            ){index ->


                HorizontalProductCard(productList[index]!! , navController)
            }
        }
    }


}