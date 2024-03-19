package com.example.bisky.data.login.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val username: String,
    val email: String,
    val avatar: String?,
    val lastOnlineDate: String?,
    val role: String?
)
