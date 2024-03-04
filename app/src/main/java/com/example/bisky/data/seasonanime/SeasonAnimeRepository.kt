package com.example.bisky.data.seasonanime

import com.example.bisky.data.seasonanime.remote.ApolloSeasonAnimeClient
import com.example.bisky.data.seasonanime.remote.model.SeasonAnime
import com.example.bisky.domain.repository.seasonanime.model.RequestSeasonAnimeParams
import javax.inject.Inject

class SeasonAnimeRepository @Inject constructor(
    private val seasonAnimeClient: ApolloSeasonAnimeClient
) {

    suspend fun getSeasonAnime(requestSeasonAnimeParams: RequestSeasonAnimeParams): List<SeasonAnime> {
        return seasonAnimeClient.getAnimeSeason(requestSeasonAnimeParams)
    }
}
