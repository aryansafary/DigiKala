package com.arysapp.digikala.ui.screens.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.arysapp.digikala.data.model.product_detail.Comment
import com.arysapp.digikala.data.model.product_detail.Price
import com.arysapp.digikala.data.model.product_detail.ProductColor
import com.arysapp.digikala.data.model.product_detail.ProductDetail
import com.arysapp.digikala.data.model.product_detail.SliderImage
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.ui.components.OurLoading
import com.arysapp.digikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {

    var productDetailList by remember {
        mutableStateOf<ProductDetail>(ProductDetail())
    }

    var imageSlider by remember {
        mutableStateOf<List<SliderImage>>(emptyList())
    }
    var productColors by remember {
        mutableStateOf<List<ProductColor>>(emptyList())
    }
    var productComments by remember {
        mutableStateOf<List<Comment>>(emptyList())
    }
    var productPriceList by remember {
        mutableStateOf<List<Price>>(emptyList())
    }

    var categoryId by remember { mutableStateOf("") }

    var description by rememberSaveable { mutableStateOf("") }

    var technicalFeatures by remember { mutableStateOf("") }

    var commentCount by remember { mutableStateOf(0) }

    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.getProductById(productId)
        viewModel.productDetail.collectLatest { productDetail ->
            when (productDetail) {
                is NetworkResult.Success -> {
                    productDetailList = productDetail.data!!
                    imageSlider = productDetail.data.imageSlider ?: emptyList()
                    productColors = productDetail.data.colors ?: emptyList()
                    productComments = productDetail.data.comments ?: emptyList()
                    productPriceList = productDetail.data.priceList ?: emptyList()
                    categoryId = productDetail.data.categoryId ?: ""
                    description = productDetail.data.description ?: ""
                    commentCount = productDetail.data.commentCount ?: 0
                    technicalFeatures = productDetail.data.technicalFeatures.toString()
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${productDetail.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }

    }

    if (loading) {
        val config = LocalConfiguration.current
        OurLoading(config.screenHeightDp.dp, true)
    } else {
        Scaffold(
            bottomBar = {
                ProductDetailBottomBar(productDetailList, navController)
            },
            topBar = {
                ProductTopAppBar(navController, productDetailList)
            }
        ) {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {

                item { ProductTopSliderSection(imageSlider) }
                item { ProductDetailHeaderSection(productDetailList) }
                item { ProductSelectColorSection(productColors) }
                item { SellerInfoSection(productDetailList.price ?: 0) }
                item { SimilarProductSection(navController, categoryId) }
                item { ProductDescriptionSection(navController, description, technicalFeatures) }
                item {
                    ProductCommentsSection(
                        navController,
                        productId,
                        productComments,
                        commentCount.toString()
                    )
                }
                item { ProductSetCommentsSection(navController, productDetailList) }
            }
        }
    }


}
