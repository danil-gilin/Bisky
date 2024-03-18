package com.example.bisky.domain.repository.seasonanime.local

import com.example.bisky.data.seasonanime.local.model.SeasonAnimeEntity

interface SeasonAnimeLocalSource {
    suspend fun insetList(list: List<SeasonAnimeEntity>)
    suspend fun fetchList(): List<SeasonAnimeEntity>
    suspend fun clear()
}
