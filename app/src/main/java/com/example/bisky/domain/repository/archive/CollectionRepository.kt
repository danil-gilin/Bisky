package com.example.bisky.domain.repository.archive

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    suspend fun getUserCollectionAnime(collection: CollectionAnime): Result<List<AnimeUserCollection>, Throwable>
    suspend fun subscribeUserCollectionAnime(collection: CollectionAnime): Flow<List<AnimeUserCollection>>
}