package com.arysapp.digikala.repository

import com.arysapp.digikala.data.model.home.StoreProduct
import com.arysapp.digikala.data.model.product_detail.ProductDetail
import com.arysapp.digikala.data.remote.BaseApiResponse
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.data.remote.ProductDetailApiInterface
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(private val api: ProductDetailApiInterface) :
    BaseApiResponse() {

    suspend fun getProductById(productId: String): NetworkResult<ProductDetail> =
        safeApiCall {
            api.getProductById(productId)
        }

    suspend fun getSimilarProducts(categoryId: String): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSimilarProducts(categoryId)
        }


}