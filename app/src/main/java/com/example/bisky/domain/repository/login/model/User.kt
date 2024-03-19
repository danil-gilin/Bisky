package com.example.bisky.domain.repository.login.model

data class User(
    val username: String,
    val email: String,
    val avatar: String?,
    val lastOnlineDate: String?,
    val role: String?
)
