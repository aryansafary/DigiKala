package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.purchase.PaymentRequest
import com.arysapp.digikala.data.model.purchase.PaymentResponse
import com.arysapp.digikala.data.model.purchase.PaymentVerificationRequest
import com.arysapp.digikala.data.model.purchase.PaymentVerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PurchaseApiInterface {

    @POST("request.json")
    suspend fun startPurchase(
        @Body paymentRequest: PaymentRequest
    ) : Response<PaymentResponse>


    @POST("verify.json")
    suspend fun verifyPurchase(
        @Body paymentVerificationRequest: PaymentVerificationRequest
    ) : Response<PaymentVerificationResponse>


}