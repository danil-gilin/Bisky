package com.example.bisky.data.anime.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusUpdateRequest(
    val status: String
)
