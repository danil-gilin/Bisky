package com.example.bisky.domain.repository.anime.remote

import com.example.GetAnimeQuery

interface AnimeRemoteSource {
    suspend fun getAnime(id: String): List<GetAnimeQuery.GetAnime>?
    suspend fun updateRatingAnime(rating: Int, animeId: String): Boolean
}
