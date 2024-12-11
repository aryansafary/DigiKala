package com.arysapp.digikala.data.model.prfile

data class LoginResponse(
    val phone: String,
    val id: String,
    val name: String,
    val role: String,
    val token: String,
)
