package com.arysapp.digikala.data.model.checkout

import com.arysapp.digikala.data.model.basket.CartItem

data class OrderFullDetail(
    val token: String,
    val _id: String,
    val userId: String,
    val orderAddress: String,
    val orderTotalDiscount: Long,
    val orderTotalPrice: Long,
    val orderUserName: String,
    val orderUserPhone: String,
    val orderStatus: String,
    val transactionId: String,
    val updatedAt: String,
    val createdAt: String,
    val orderProducts: List<CartItem>
)
