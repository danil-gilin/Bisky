package com.example.bisky.data.searchanime.remote.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SkipListResponse(
    val animeId: String
)
