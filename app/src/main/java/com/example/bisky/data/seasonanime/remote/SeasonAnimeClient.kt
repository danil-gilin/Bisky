package com.example.bisky.data.seasonanime.remote

import com.example.bisky.data.seasonanime.remote.model.SeasonAnime
import com.example.bisky.domain.repository.seasonanime.model.RequestSeasonAnimeParams

interface SeasonAnimeClient {
    suspend fun getAnimeSeason(params: RequestSeasonAnimeParams): List<SeasonAnime>
}
