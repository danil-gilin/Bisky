package com.example.bisky.domain.repository.genre.remote

import com.example.GetGenresQuery

interface GenreRemoteSource {
    suspend fun getGenres(page: Int): List<GetGenresQuery.GetGenre>
}