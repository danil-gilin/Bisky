package com.example.bisky.domain.repository.genre.model

data class Genre(
    val _id: String,
    val description: String?,
    val name: String?,
    val posters: List<String>,
)
