package com.example.bisky.data.seasonanime.remote.model

data class SeasonAnime(
    val poster: String?,
    val labels: List<String>,
    val genres: List<Genre>,
    val description: String?,
    val screenshots: List<String>,
    val scores: Double
)

data class Genre(
    val name: Name
)

data class Name(
    val en: String,
    val ru: String
)
