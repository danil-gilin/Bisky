package com.example.bisky.domain.repository.newseries

import com.example.GetNewSeriesAnimeQuery
import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.newseries.model.NewSeriesAnime

interface NewSeriesAnimeRepository {
    suspend fun getNewSeriesAnime(): Result<List<NewSeriesAnime>, Throwable>
}