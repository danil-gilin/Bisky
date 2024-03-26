package com.example.bisky.data.anime.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatingUpdateRequest(
    val score: Int
)
