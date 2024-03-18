package com.example.bisky.domain.repository.genre

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.genre.model.Genre

interface GenreRepository {
    suspend fun getGenres(page: Int): Result<List<Genre>, Throwable>
}