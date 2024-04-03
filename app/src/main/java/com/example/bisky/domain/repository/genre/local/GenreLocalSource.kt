package com.example.bisky.domain.repository.genre.local

import com.example.bisky.data.genre.local.model.GenreEntity

interface GenreLocalSource {
    suspend fun fetchGenres(): List<GenreEntity>
    suspend fun insertGenres(genres: List<GenreEntity>)
}