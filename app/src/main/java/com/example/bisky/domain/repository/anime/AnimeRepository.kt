package com.example.bisky.domain.repository.anime

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.anime.model.Anime

interface AnimeRepository {
    suspend fun getAnime(id: String): Result<Anime?, Throwable>
    suspend fun updateAnimeApi(rating: Int, animeId: String): Result<Boolean, Throwable>
}
