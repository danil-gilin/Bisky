package com.example.bisky.domain.repository.archive.model

import com.example.bisky.domain.repository.anime.model.Episodes
data class AnimeQuickSelect(
    val poster: String?,
    val label: String?,
    val id: String,
    val episodes: Episodes,
    val status: String?,
    val screenshots: List<String>,
    val kind: String,
)
