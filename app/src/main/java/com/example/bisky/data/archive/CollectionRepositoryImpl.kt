package com.example.bisky.data.archive

import com.example.bisky.data.archive.mapper.mapToDomain
import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.domain.repository.archive.local.CollectionLocalSource
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import com.example.bisky.domain.repository.archive.remote.CollectionRemoteSource
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionRemoteSource: CollectionRemoteSource,
    private val collectionLocalSource: CollectionLocalSource,
    private val resultWrapper: ResultWrapper
) : CollectionRepository {

    override suspend fun getUserCollectionAnimePagging(
        collection: CollectionAnime,
        page: Int,
        searchInput: String
    ): Result<List<AnimeUserCollection>, Throwable> = resultWrapper.wrap {
        val response = collectionRemoteSource.getUserCollectionAnimePagging(collection, page, searchInput).map { it.mapToDomain() }
        response
    }


    override suspend fun subscribeUserCollectionAnime(collection: CollectionAnime) =
        collectionLocalSource.subscribeAnimeCollection(collection)

    override suspend fun getUserCollectionQuickSelectAnime(
        collection: CollectionAnime,
        count: Int
    ) = resultWrapper.wrap {
        collectionRemoteSource.getUserCollectionQuickSelectAnime(collection, count).map {
            it.mapToDomain()
        }
    }
}
