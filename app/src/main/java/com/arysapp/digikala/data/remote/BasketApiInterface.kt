package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.ResponseResult
import com.arysapp.digikala.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET


interface BasketApiInterface {

    @GET("v1/getAllProducts")
    suspend fun getSuggestedItems(): Response<ResponseResult<List<StoreProduct>>>

}