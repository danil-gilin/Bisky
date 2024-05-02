package com.example.bisky.data.anime.local

import com.example.bisky.data.archive.local.AddCollectionDao
import com.example.bisky.data.archive.local.CompleteCollectionDao
import com.example.bisky.data.archive.local.WatchCollectionDao
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.anime.local.AnimeLocalSource
import com.example.bisky.domain.repository.archive.local.model.AddAnime
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import com.example.bisky.domain.repository.archive.local.model.WatchAnime
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeLocalSourceImpl @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val addCollectionDao: AddCollectionDao,
    private val watchCollectionDao: WatchCollectionDao,
    private val completeCollectionDao: CompleteCollectionDao
) : AnimeLocalSource {
    override suspend fun addToWatchCollection(watchAnime: WatchAnime) =
        withContext(dispatchersProvider.io) {
            watchCollectionDao.insert(watchAnime)
        }

    override suspend fun addToCompleteCollection(completeAnime: CompleteAnime) =
        withContext(dispatchersProvider.io) {
            completeCollectionDao.insert(completeAnime)
        }
    override suspend fun addToAddCollection(addAnime: AddAnime) =
        withContext(dispatchersProvider.io) {
            addCollectionDao.insert(addAnime)
        }

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
}
