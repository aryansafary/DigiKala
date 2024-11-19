package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.ResponseResult
import com.arysapp.digikala.data.model.home.Slider
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {
    @GET("v1/getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>
}