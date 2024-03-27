package com.example.bisky.data.anime.remote.model

import com.example.bisky.data.network.moshi.AlwaysSerializeNulls
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@AlwaysSerializeNulls
data class RatingUpdateRequest(
   val score: Int?
)
