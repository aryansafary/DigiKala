package com.arysapp.digikala.data.model.purchase

data class PaymentResponse(
    val data: Data,
    val errors: List<Any>
)

data class Data(
    val authority: String,
    val fee: Int,
    val fee_type: String,
    val code: Int,
    val message: String
)
