package com.example.bisky.data.archive

import com.example.bisky.data.archive.mapper.mapToDomain
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.ArchiveRepository
import com.example.bisky.domain.repository.archive.remote.ArchiveRemoteSource
import javax.inject.Inject

class ArchiveRepositoryImpl @Inject constructor(
    private val archiveRemoteSource: ArchiveRemoteSource,
    private val resultWrapper: ResultWrapper
) : ArchiveRepository {

    override suspend fun getUserCollectionAnime(collection: CollectionAnime) = resultWrapper.wrap {
        archiveRemoteSource.getUserCollectionAnime(collection).map { it.mapToDomain() }
    }
}
