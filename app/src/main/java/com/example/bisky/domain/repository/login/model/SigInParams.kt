package com.example.bisky.domain.repository.login.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SigInParams(
    val username: String,
    val password: String
)
