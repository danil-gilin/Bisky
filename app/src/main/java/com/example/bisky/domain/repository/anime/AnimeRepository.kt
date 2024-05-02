package com.example.bisky.domain.repository.anime

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.anime.model.Anime
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.local.model.BaseAnimeCollection

interface AnimeRepository {
    suspend fun getAnime(id: String): Result<Anime?, Throwable>
    suspend fun updateAnimeApi(rating: Int?, animeId: String): Result<Boolean, Throwable>
    suspend fun updateCollection(collectionType: CollectionAnime, animeId: String): Result<Boolean, Throwable>
}
