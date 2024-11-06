package com.arysapp.digikala.repository

import com.arysapp.digikala.data.remote.BaseApiResponse
import com.arysapp.digikala.data.remote.HomeApiInterface
import javax.inject.Inject

class HomeRepository @Inject constructor(api: HomeApiInterface) : BaseApiResponse() {

}