package com.example.bisky.domain.repository.archive

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.model.AnimeQuickSelect
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    suspend fun subscribeUserCollectionAnime(collection: CollectionAnime): Flow<List<AnimeUserCollection>>
    suspend fun getUserCollectionQuickSelectAnime(collection: CollectionAnime, count: Int): Result<List<AnimeQuickSelect>, Throwable>
    suspend fun getUserCollectionAnimePagging(
        collection: CollectionAnime,
        page: Int,
        searchInput: String,
        needClear: Boolean
    ): Result<List<AnimeUserCollection>, Throwable>
}