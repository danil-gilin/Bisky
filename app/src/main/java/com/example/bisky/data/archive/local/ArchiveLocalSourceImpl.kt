package com.example.bisky.data.archive.local

import com.example.bisky.data.archive.mapper.mapAddToAnimeUserCollection
import com.example.bisky.data.archive.mapper.mapCompleteToAnimeUserCollection
import com.example.bisky.data.archive.mapper.mapWatchToAnimeUserCollection
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.local.CollectionLocalSource
import com.example.bisky.domain.repository.archive.local.model.AddAnime
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import com.example.bisky.domain.repository.archive.local.model.WatchAnime
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ArchiveLocalSourceImpl(
    private val dispatchersProvider: DispatchersProvider,
    private val addCollectionDao: AddCollectionDao,
    private val watchCollectionDao: WatchCollectionDao,
    private val completeCollectionDao: CompleteCollectionDao
) : CollectionLocalSource {
    override suspend fun addToWatchCollection(watchAnime: List<WatchAnime>) =
        withContext(dispatchersProvider.io) {
            watchCollectionDao.insertList(watchAnime)
        }

    override suspend fun addToCompleteCollection(completeAnime: List<CompleteAnime>) =
        withContext(dispatchersProvider.io) {
            completeCollectionDao.insertList(completeAnime)
        }

    override suspend fun addToAddCollection(addAnime: List<AddAnime>) =
        withContext(dispatchersProvider.io) {
            addCollectionDao.insertList(addAnime)
        }

    override suspend fun clearAnimeCollection(id: String) =
        withContext(dispatchersProvider.io) {
            addCollectionDao.deleteAnime(id)
            watchCollectionDao.deleteAnime(id)
            completeCollectionDao.deleteAnime(id)
        }

    override suspend fun getAnimeCollection(collection: CollectionAnime): List<AnimeUserCollection> {
        return when (collection) {
            CollectionAnime.ADDED -> {
                addCollectionDao.fetchAll().mapAddToAnimeUserCollection()
            }

            CollectionAnime.COMPLETED -> {
                completeCollectionDao.fetchAll().mapCompleteToAnimeUserCollection()
            }

            CollectionAnime.WATCHING -> {
                watchCollectionDao.fetchAll().mapWatchToAnimeUserCollection()
            }

            else -> {
                emptyList()
            }
        }
    }

    override suspend fun subscribeAnimeCollection(collection: CollectionAnime): Flow<List<AnimeUserCollection>> {
        return when (collection) {
            CollectionAnime.ADDED -> {
                addCollectionDao.subscribe().map { it.mapAddToAnimeUserCollection() }
            }

            CollectionAnime.COMPLETED -> {
                completeCollectionDao.subscribe().map { it.mapCompleteToAnimeUserCollection() }
            }

            CollectionAnime.WATCHING -> {
                watchCollectionDao.subscribe().map { it.mapWatchToAnimeUserCollection() }
            }

            else -> {
                flow<List<AnimeUserCollection>> { }
            }
        }
    }
}
