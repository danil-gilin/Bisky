package com.example.bisky.domain.repository.genre

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.genre.model.AnimeGenre
import com.example.bisky.domain.repository.genre.model.Genre
import com.example.bisky.domain.repository.genre.model.GenreSimple

interface GenreRepository {
    suspend fun getGenres(page: Int): Result<List<Genre>, Throwable>
    suspend fun getAnimesGenre(genreId: String, page: Int): Result<List<AnimeGenre>, Throwable>
    suspend fun getAllGenresWithSimpleInfo(): Result<List<GenreSimple>, Throwable>
}