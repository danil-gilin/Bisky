package com.example.bisky.data.seasonanime

import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.seasonanime.mapper.mapToDomain
import com.example.bisky.data.seasonanime.mapper.mapToEntity
import com.example.bisky.domain.repository.seasonanime.SeasonAnimeRepository
import com.example.bisky.domain.repository.seasonanime.local.SeasonAnimeLocalSource
import com.example.bisky.domain.repository.seasonanime.model.RequestSeasonAnimeParams
import com.example.bisky.domain.repository.seasonanime.remote.SeasonAnimeRemoteSource
import javax.inject.Inject

class SeasonAnimeRepositoryImpl @Inject constructor(
    private val seasonAnimeClient: SeasonAnimeRemoteSource,
    private val seasonAnimeLocalSource: SeasonAnimeLocalSource,
    private val resultWrapper: ResultWrapper
) : SeasonAnimeRepository {

    override suspend fun getSeasonAnime(
        requestSeasonAnimeParams: RequestSeasonAnimeParams
    ) = resultWrapper.wrap {
        val response = seasonAnimeClient.getAnimeSeason(requestSeasonAnimeParams)

        seasonAnimeLocalSource
            .insetList(response?.map { it.mapToEntity() }.orEmpty())

        seasonAnimeLocalSource.fetchList().map { it.mapToDomain() }
    }
}
