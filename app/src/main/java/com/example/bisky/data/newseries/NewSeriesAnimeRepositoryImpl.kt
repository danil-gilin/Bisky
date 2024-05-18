package com.example.bisky.data.newseries

import com.example.GetNewSeriesAnimeQuery
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.newseries.NewSeriesAnimeRemoteSource
import com.example.bisky.domain.repository.newseries.NewSeriesAnimeRepository
import javax.inject.Inject

class NewSeriesAnimeRepositoryImpl @Inject constructor(
    private val remoteSource: NewSeriesAnimeRemoteSource,
    private val resultWrapper: ResultWrapper
): NewSeriesAnimeRepository {
    override suspend fun getNewSeriesAnime() = resultWrapper.wrap {
        remoteSource.getNewSeriesAnime().map { it.mapToDomain() }
    }
}
