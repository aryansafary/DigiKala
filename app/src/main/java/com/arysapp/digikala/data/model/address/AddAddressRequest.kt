package com.arysapp.digikala.data.model.address

data class AddAddressRequest(
    val token: String,
    val address: String,
    val postalCode: String,
    val phone: String,
    val name: String
)
