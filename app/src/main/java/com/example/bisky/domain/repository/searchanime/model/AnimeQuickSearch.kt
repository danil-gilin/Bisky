package com.example.bisky.domain.repository.searchanime.model

import com.example.bisky.domain.repository.anime.model.Episodes
import com.example.bisky.domain.repository.anime.model.Franchise
import com.example.bisky.domain.repository.anime.model.Score
import com.example.bisky.domain.repository.anime.model.Studio

data class AnimeQuickSearch(
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
    val score: Score,
    val age: String,
)


