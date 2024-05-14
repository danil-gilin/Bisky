package com.example.bisky.domain.repository.anime

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.anime.model.Anime
import com.example.bisky.domain.repository.anime.model.CollectionAnime

interface AnimeRepository {
    suspend fun getAnime(id: String): Result<Anime?, Throwable>
    suspend fun updateAnimeScore(rating: Int?, animeId: String): Result<Boolean, Throwable>
    suspend fun updateCollection(collectionType: CollectionAnime, animeId: String): Result<Boolean, Throwable>
}
