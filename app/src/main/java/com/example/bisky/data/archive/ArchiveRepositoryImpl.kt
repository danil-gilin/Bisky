package com.example.bisky.data.archive

import com.example.bisky.data.anime.local.AnimeLocalSourceImpl
import com.example.bisky.data.archive.mapper.mapToAddCollection
import com.example.bisky.data.archive.mapper.mapToCompleteCollection
import com.example.bisky.data.archive.mapper.mapToDomain
import com.example.bisky.data.archive.mapper.mapToWatchCollection
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.ArchiveRepository
import com.example.bisky.domain.repository.archive.local.ArchiveLocalSource
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import com.example.bisky.domain.repository.archive.remote.ArchiveRemoteSource
import javax.inject.Inject

class ArchiveRepositoryImpl @Inject constructor(
    private val archiveRemoteSource: ArchiveRemoteSource,
    private val archiveLocalSource: ArchiveLocalSource,
    private val resultWrapper: ResultWrapper
) : ArchiveRepository {

    override suspend fun getUserCollectionAnime(collection: CollectionAnime) = resultWrapper.wrap {
       val response = archiveRemoteSource.getUserCollectionAnime(collection).map { it.mapToDomain() }
        when(collection) {
            CollectionAnime.ADDED ->{
                archiveLocalSource.addToAddCollection(response.mapToAddCollection())
            }
            CollectionAnime.COMPLETED -> {
                archiveLocalSource.addToCompleteCollection(response.mapToCompleteCollection())
            }
            CollectionAnime.WATCHING -> {
                archiveLocalSource.addToWatchCollection(response.mapToWatchCollection())
            }

            else -> {
                emptyList<AnimeUserCollection>()
            }
        }
        archiveLocalSource.getAnimeCollection(collection)
    }

    override suspend fun subscribeUserCollectionAnime(collection: CollectionAnime) =
        archiveLocalSource.subscribeAnimeCollection(collection)
}
