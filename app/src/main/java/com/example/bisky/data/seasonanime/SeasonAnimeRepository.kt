package com.example.bisky.data.seasonanime

import com.example.bisky.data.seasonanime.remote.ApolloSeasonAnimeClient
import com.example.bisky.data.seasonanime.remote.model.SeasonAnime
import javax.inject.Inject

class SeasonAnimeRepository @Inject constructor(
    private val seasonAnimeClient: ApolloSeasonAnimeClient
) {

    suspend fun getSeasonAnime(): List<SeasonAnime> {
        return seasonAnimeClient.getAnimeSeason()
    }
}
