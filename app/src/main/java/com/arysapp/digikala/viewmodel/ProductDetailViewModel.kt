package com.arysapp.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.arysapp.digikala.data.model.home.StoreProduct
import com.arysapp.digikala.data.model.product_detail.ProductDetail
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.repository.ProductDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: ProductDetailRepository) :
    ViewModel() {

    val productDetail = MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())

    val similarProducts =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


    fun getProductById(productId: String) {
        viewModelScope.launch {
            productDetail.emit(repository.getProductById(productId))
        }
    }

    fun getSimilarProducts(categoryId: String) {
        viewModelScope.launch {
            similarProducts.emit(repository.getSimilarProducts(categoryId))
        }
    }


}