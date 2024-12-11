package com.arysapp.digikala.data.model.purchase

data class PaymentRequest(
    val merchant_id: String,
    val amount: String,
    val callback_url: String,
    val description: String
)
