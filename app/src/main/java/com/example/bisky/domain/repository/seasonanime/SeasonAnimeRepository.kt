package com.example.bisky.domain.repository.seasonanime

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.seasonanime.model.RequestSeasonAnimeParams
import com.example.bisky.domain.repository.seasonanime.model.SeasonAnime

interface SeasonAnimeRepository {
    suspend fun getSeasonAnime(
        requestSeasonAnimeParams: RequestSeasonAnimeParams
    ): Result<List<SeasonAnime>, Throwable>
}
