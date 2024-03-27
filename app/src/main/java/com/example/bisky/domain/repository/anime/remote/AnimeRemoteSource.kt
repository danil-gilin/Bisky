package com.example.bisky.domain.repository.anime.remote

import com.example.GetAnimeQuery
import com.example.bisky.domain.repository.anime.model.Collection

interface AnimeRemoteSource {
    suspend fun getAnime(id: String): List<GetAnimeQuery.GetAnime>?
    suspend fun updateRatingAnime(rating: Int?, animeId: String): Boolean
    suspend fun updateCollection(collectionType: Collection, animeId: String): Boolean
}
