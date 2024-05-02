package com.example.bisky.data.anime

import com.example.bisky.data.anime.mapper.mapToDomain
import com.example.bisky.data.archive.mapper.mapToAddCollection
import com.example.bisky.data.archive.mapper.mapToCompleteCollection
import com.example.bisky.data.archive.mapper.mapToDomain
import com.example.bisky.data.archive.mapper.mapToWatchCollection
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.anime.AnimeRepository
import com.example.bisky.domain.repository.anime.remote.AnimeRemoteSource
import com.example.bisky.domain.repository.anime.local.AnimeLocalSource
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.remote.CollectionRemoteSource
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeClient: AnimeRemoteSource,
    private val animeLocalSource: AnimeLocalSource,
    private val resultWrapper: ResultWrapper,
    private val archiveRemoteSource: CollectionRemoteSource
) : AnimeRepository {

    override suspend fun getAnime(id: String) = resultWrapper.wrap {
        animeClient.getAnime(id)?.firstOrNull()?.mapToDomain()
    }

    override suspend fun updateAnimeApi(rating: Int?, animeId: String) = resultWrapper.wrap {
        animeClient.updateRatingAnime(rating, animeId)
    }

    override suspend fun updateCollection(collectionType: CollectionAnime, animeId: String) = resultWrapper.wrap  {
        val response = animeClient.updateCollection(collectionType, animeId)
        val updatedCollection = archiveRemoteSource.getUserCollectionAnime(collectionType).map { it.mapToDomain() }
        animeLocalSource.clearAnimeCollection(animeId)
        when(collectionType) {
            CollectionAnime.ADDED -> animeLocalSource.addToAddCollection(updatedCollection.mapToAddCollection())
            CollectionAnime.COMPLETED -> animeLocalSource.addToCompleteCollection(updatedCollection.mapToCompleteCollection())
            CollectionAnime.WATCHING -> animeLocalSource.addToWatchCollection(updatedCollection.mapToWatchCollection())
            else -> Unit
        }
        response
    }
}
