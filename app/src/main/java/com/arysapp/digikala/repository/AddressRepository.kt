package com.arysapp.digikala.repository

import com.arysapp.digikala.data.model.address.AddAddressRequest
import com.arysapp.digikala.data.model.address.UserAddress
import com.arysapp.digikala.data.remote.*
import javax.inject.Inject

class AddressRepository @Inject constructor(private val api: AddressApiInterface) : BaseApiResponse() {

    suspend fun getUserAddressList(token: String): NetworkResult<List<UserAddress>> =
        safeApiCall {
          api.getUserAddressList(token)
        }


    suspend fun setNewAddress(address: AddAddressRequest): NetworkResult<String> =
        safeApiCall {
            api.saveUserAddress(address)
        }

}