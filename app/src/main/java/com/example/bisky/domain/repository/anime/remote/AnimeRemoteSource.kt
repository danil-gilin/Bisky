package com.example.bisky.domain.repository.anime.remote

import com.example.GetAnimeQuery
import com.example.bisky.domain.repository.anime.model.CollectionAnime

interface AnimeRemoteSource {
    suspend fun getAnime(id: String): List<GetAnimeQuery.GetAnime>?
    suspend fun updateRatingAnime(rating: Int?, animeId: String): Boolean
    suspend fun updateCollection(collectionType: CollectionAnime, animeId: String): Boolean
}
