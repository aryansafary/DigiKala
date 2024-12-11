package com.arysapp.digikala.data.model.purchase

data class PaymentVerificationRequest(
    val merchant_id: String,
    val authority: String,
    val amount: String
)
