package com.example.bisky.data.login.remote.model

data class UserResponse(
    val _id: String,
    val email: String,
    val image: String,
    val name: String,
    val password: String,
    val refreshToken: String,
    val role: String,
    val username: String
)