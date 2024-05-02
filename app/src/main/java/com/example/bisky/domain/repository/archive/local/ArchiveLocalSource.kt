package com.example.bisky.domain.repository.archive.local

import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.local.model.AddAnime
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import com.example.bisky.domain.repository.archive.local.model.WatchAnime
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import kotlinx.coroutines.flow.Flow

interface ArchiveLocalSource {
    suspend fun addToCompleteCollection(completeAnime: List<CompleteAnime>)
    suspend fun addToWatchCollection(watchAnime: List<WatchAnime>)
    suspend fun addToAddCollection(addAnime: List<AddAnime>)
    suspend fun clearAnimeCollection(id: String)
    suspend fun getAnimeCollection(collection: CollectionAnime): List<AnimeUserCollection>
    suspend fun subscribeAnimeCollection(collection: CollectionAnime): Flow<List<AnimeUserCollection>>
}