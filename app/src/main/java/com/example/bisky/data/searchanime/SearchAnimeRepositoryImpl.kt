package com.example.bisky.data.searchanime

import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.searchanime.mapper.mapToDomain
import com.example.bisky.domain.repository.searchanime.SearchAnimeRepository
import com.example.bisky.domain.repository.searchanime.remote.SearchAnimeRemoteSource
import javax.inject.Inject

class SearchAnimeRepositoryImpl @Inject constructor(
    private val searchAnimeRemoteSource: SearchAnimeRemoteSource,
    private val resultWrapper: ResultWrapper
): SearchAnimeRepository {
    override suspend fun getAnimes(input: String)= resultWrapper.wrap {
        searchAnimeRemoteSource.getAnimes(input).map { it.mapToDomain() }
    }
}