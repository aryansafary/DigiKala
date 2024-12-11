package com.arysapp.digikala.repository

import com.arysapp.digikala.data.model.checkout.ConfirmPurchase
import com.arysapp.digikala.data.model.checkout.OrderDetail
import com.arysapp.digikala.data.remote.*
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val api: CheckoutApiInterface) : BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> =
        safeApiCall {
           api.getShippingCost(address)
        }


    suspend fun setNewOrder(cartOrderDetail: OrderDetail): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(cartOrderDetail)
        }

    suspend fun confirmPurchase(confirmPurchase: ConfirmPurchase): NetworkResult<String> =
        safeApiCall {
            api.confirmPurchase(confirmPurchase)
        }

}