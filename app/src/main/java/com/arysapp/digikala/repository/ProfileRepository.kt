package com.arysapp.digikala.repository

import com.arysapp.digikala.data.model.checkout.OrderFullDetail
import com.arysapp.digikala.data.model.prfile.LoginRequest
import com.arysapp.digikala.data.model.prfile.LoginResponse
import com.arysapp.digikala.data.model.prfile.SetUserNameRequest
import com.arysapp.digikala.data.remote.BaseApiResponse
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.data.remote.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ProfileApiInterface) :
    BaseApiResponse() {

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(loginRequest)
        }

    suspend fun setUserName(newUserName: SetUserNameRequest): NetworkResult<String> =
        safeApiCall {
            api.setUserName(newUserName)
        }


    suspend fun getUserOrders(token: String): NetworkResult<List<OrderFullDetail>>  =
        safeApiCall {
            api.getUserOrders(token)
        }

}