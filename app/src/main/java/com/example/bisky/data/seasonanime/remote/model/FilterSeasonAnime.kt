package com.example.bisky.data.seasonanime.remote.model

data class FilterSeasonAnime(
    val screenshotsCount: Int,
    val labelCount: Int,
    val airedOn: AiredOn
)

data class AiredOn(
    val from: String,
    val to: String
)

