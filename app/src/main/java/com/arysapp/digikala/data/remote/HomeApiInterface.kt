package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.ResponseResult
import com.arysapp.digikala.data.model.home.AmazingItem
import com.arysapp.digikala.data.model.home.Slider
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {
    @GET("v1/getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>

    @GET("v1/getAmazingProducts")
    suspend fun getAmazingItem(): Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/getSuperMarketAmazingProducts")
    suspend fun getAmazingSuperMarketItems() : Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/get4Banners")
    suspend fun getProposalBanners() : Response<ResponseResult<List<Slider>>>

}