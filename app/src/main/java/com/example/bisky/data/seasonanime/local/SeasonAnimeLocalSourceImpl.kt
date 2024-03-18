package com.example.bisky.data.seasonanime.local

import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.seasonanime.local.model.SeasonAnimeEntity
import com.example.bisky.domain.repository.seasonanime.local.SeasonAnimeLocalSource
import javax.inject.Inject
import kotlinx.coroutines.withContext

class SeasonAnimeLocalSourceImpl @Inject constructor(
    private val seasonAnimeDao: SeasonAnimeDao,
    private val dispatchersProvider: DispatchersProvider
) : SeasonAnimeLocalSource {

    override suspend fun insetList(list: List<SeasonAnimeEntity>) =
        withContext(dispatchersProvider.io) {
            clear()
            seasonAnimeDao.insertList(list)
        }

    override suspend fun clear() =
        withContext(dispatchersProvider.io) {
            seasonAnimeDao.clear()
        }

    override suspend fun fetchList() =
        withContext(dispatchersProvider.io) {
            seasonAnimeDao.fetchListSeasonAnime()
        }
}
