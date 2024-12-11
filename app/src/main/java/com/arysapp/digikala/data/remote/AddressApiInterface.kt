package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.ResponseResult
import com.arysapp.digikala.data.model.address.AddAddressRequest
import com.arysapp.digikala.data.model.address.UserAddress
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("v1/getUserAddress")
    suspend fun getUserAddressList(
        @Query("token") token: String
    ): Response<ResponseResult<List<UserAddress>>>


    @POST("v1/saveUserAddress")
    suspend fun saveUserAddress(
        @Body address: AddAddressRequest
    ): Response<ResponseResult<String>>

}