package com.example.bisky.domain.repository.archive.remote

import com.example.GetUserCollectionAnimeQuery
import com.example.bisky.domain.repository.anime.model.CollectionAnime

interface ArchiveRemoteSource {
    suspend fun getUserCollectionAnime(collection: CollectionAnime): List<GetUserCollectionAnimeQuery.AnimeEstimate>
}