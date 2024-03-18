package com.example.bisky.domain.repository.seasonanime.model

data class SeasonAnime(
    val _id: String,
    val poster: String?,
    val labels: String?,
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
