package com.example.bisky.data.seasonanime.remote

import com.example.bisky.data.seasonanime.remote.model.SeasonAnime

interface SeasonAnimeClient {
    suspend fun getAnimeSeason(): List<SeasonAnime>
}
