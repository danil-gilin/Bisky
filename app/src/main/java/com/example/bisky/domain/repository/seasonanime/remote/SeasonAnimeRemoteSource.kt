package com.example.bisky.domain.repository.seasonanime.remote

import com.example.SeasonAnimeQuery
import com.example.bisky.domain.repository.seasonanime.model.RequestSeasonAnimeParams

interface SeasonAnimeRemoteSource {
    suspend fun getAnimeSeason(params: RequestSeasonAnimeParams): List<SeasonAnimeQuery.GetAnime>?
}
