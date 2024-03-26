package com.example.bisky.domain.repository.anime.model

data class Anime(
    val poster: String?,
    val label: String?,
    val _id: String,
    val dates: String?,
    val description: String?,
    val episodes: Episodes,
    val franchise: Franchise?,
    val genres: List<String>,
    val status: String?,
    val studios: List<Studio>,
    val screenshots: List<String>,
    val kind: String,
    val usersList: UsersList,
    val videos: List<Video>,
    val score: Score,
    val age: String,
    val similarAnime: List<SimilarAnime>
)

data class SimilarAnime(
    val id: String,
    val poster: String?,
    val name: String?,
    val rating: Double
)

data class Episodes(
    val averageDuration: Int,
    val count: Int?,
)

data class Franchise(
    val _id: String,
    val name: String?,
)

data class Studio(
    val name: String,
    val logo: String?,
)

data class UsersList(
    val addedCount: Int,
    val completedCount: Int,
    val droppedCount: Int,
    val generalCount: Int,
    val watchingCount: Int,
)

data class Video(
    val url: String,
    val name: String?,
)

data class Score(
    val averageScore: Double,
    val count: Int,
)
