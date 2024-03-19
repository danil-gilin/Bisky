package com.example.bisky.domain.repository.login.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SigUpParams(
    val username: String,
    val password: String,
    val email: String
)
