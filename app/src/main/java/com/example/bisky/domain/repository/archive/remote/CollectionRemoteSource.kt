package com.example.bisky.domain.repository.archive.remote

import com.example.GetUserCollectionAnimeQuery
import com.example.GetUserCollectionQuickSelectAnimeQuery
import com.example.bisky.domain.repository.anime.model.CollectionAnime

interface CollectionRemoteSource {
    suspend fun getUserCollectionAnime(collection: CollectionAnime): List<GetUserCollectionAnimeQuery.AnimeEstimate>
    suspend fun getUserCollectionQuickSelectAnime(
        collection: CollectionAnime,
        count: Int
    ): List<GetUserCollectionQuickSelectAnimeQuery.AnimeEstimate>
}