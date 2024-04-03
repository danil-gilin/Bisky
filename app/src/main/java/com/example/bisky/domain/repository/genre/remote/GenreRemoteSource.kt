package com.example.bisky.domain.repository.genre.remote

import com.example.GenreAnimeQuery
import com.example.GetAllGenreWithSimpleInfoQuery
import com.example.GetGenresQuery

interface GenreRemoteSource {
    suspend fun getGenres(page: Int): List<GetGenresQuery.GetGenre>
    suspend fun getAnimesGenre(genreId: String, page: Int): List<GenreAnimeQuery.GetAnime>
    suspend fun getAllGenre(): List<GetAllGenreWithSimpleInfoQuery.GetGenre>
}