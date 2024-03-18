package com.example.bisky.domain.repository.login.model

data class User(
    val _id: String,
    val email: String,
    val image: String?,
    val name: String?,
    val password: String?,
    val refreshToken: String?,
    val role: String?,
    val username: String
)
