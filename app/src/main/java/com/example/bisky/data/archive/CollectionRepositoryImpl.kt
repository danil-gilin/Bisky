package com.example.bisky.data.archive

import com.example.bisky.data.archive.mapper.mapToAddCollection
import com.example.bisky.data.archive.mapper.mapToCompleteCollection
import com.example.bisky.data.archive.mapper.mapToDomain
import com.example.bisky.data.archive.mapper.mapToWatchCollection
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

    override suspend fun getUserCollectionAnime(collection: CollectionAnime) = resultWrapper.wrap {
       val response = collectionRemoteSource.getUserCollectionAnime(collection).map { it.mapToDomain() }
        collectionLocalSource.clearAnimeCollection(collection)
        when(collection) {
            CollectionAnime.ADDED ->{
                collectionLocalSource.addToAddCollection(response.mapToAddCollection())
            }
            CollectionAnime.COMPLETED -> {
                collectionLocalSource.addToCompleteCollection(response.mapToCompleteCollection())
            }
            CollectionAnime.WATCHING -> {
                collectionLocalSource.addToWatchCollection(response.mapToWatchCollection())
            }

            else -> {
                emptyList<AnimeUserCollection>()
            }
        }
        collectionLocalSource.getAnimeCollection(collection)
    }

    override suspend fun getUserCollectionAnimePagging(
        collection: CollectionAnime,
        page: Int,
        searchInput: String,
        needClear: Boolean
    ): Result<List<AnimeUserCollection>, Throwable> = resultWrapper.wrap {
        val response = collectionRemoteSource.getUserCollectionAnimePagging(collection, page, searchInput).map { it.mapToDomain() }
        if (page == 1 || needClear) {
            collectionLocalSource.clearAnimeCollection(collection)
        }
        when(collection) {
            CollectionAnime.ADDED ->{
                collectionLocalSource.addToAddCollection(response.mapToAddCollection())
            }
            CollectionAnime.COMPLETED -> {
                collectionLocalSource.addToCompleteCollection(response.mapToCompleteCollection())
            }
            CollectionAnime.WATCHING -> {
                collectionLocalSource.addToWatchCollection(response.mapToWatchCollection())
            }

            else -> {
                emptyList<AnimeUserCollection>()
            }
        }
        collectionLocalSource.getAnimeCollection(collection)
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
