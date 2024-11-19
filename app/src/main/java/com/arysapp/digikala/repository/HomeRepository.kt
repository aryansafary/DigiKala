package com.arysapp.digikala.repository

import com.arysapp.digikala.data.model.home.Slider
import com.arysapp.digikala.data.remote.BaseApiResponse
import com.arysapp.digikala.data.remote.HomeApiInterface
import com.arysapp.digikala.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiInterface: HomeApiInterface) : BaseApiResponse() {

    suspend fun getSlider(): NetworkResult<List<Slider>>
    = safeApiCall {
            apiInterface.getSlider()
        }
}