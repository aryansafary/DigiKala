package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.ResponseResult
import com.arysapp.digikala.data.model.home.StoreProduct
import com.arysapp.digikala.data.model.product_detail.ProductDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailApiInterface {

    @GET("v1/getProductById")
    suspend fun getProductById(
        @Query("id") productId: String
    ): Response<ResponseResult<ProductDetail>>

    @GET("v1/getSimilarProducts")
    suspend fun getSimilarProducts(
        @Query("categoryId") categoryId: String
    ): Response<ResponseResult<List<StoreProduct>>>


}