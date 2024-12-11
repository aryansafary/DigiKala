package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.ResponseResult
import com.arysapp.digikala.data.model.checkout.OrderFullDetail
import com.arysapp.digikala.data.model.prfile.LoginRequest
import com.arysapp.digikala.data.model.prfile.LoginResponse
import com.arysapp.digikala.data.model.prfile.SetUserNameRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileApiInterface {

    @POST("v1/login")
    suspend fun login(
        @Body login : LoginRequest
    ) : Response<ResponseResult<LoginResponse>>


    @POST("v1/setUserName")
    suspend fun setUserName(
        @Body setUserName : SetUserNameRequest
    ) : Response<ResponseResult<String>>



    @GET("v1/getUserOrders")
    suspend fun getUserOrders(
        @Query("token") token: String
    ): Response<ResponseResult<List<OrderFullDetail>>>

}