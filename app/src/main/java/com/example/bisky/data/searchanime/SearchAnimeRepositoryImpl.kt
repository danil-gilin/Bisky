package com.example.bisky.data.searchanime

import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.searchanime.mapper.mapToDomain
import com.example.bisky.domain.repository.searchanime.SearchAnimeRepository
import com.example.bisky.domain.repository.searchanime.model.FilterSearch
import com.example.bisky.domain.repository.searchanime.remote.SearchAnimeRemoteSource
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

class SearchAnimeRepositoryImpl @Inject constructor(
    private val searchAnimeRemoteSource: SearchAnimeRemoteSource,
    private val resultWrapper: ResultWrapper
): SearchAnimeRepository {
    val localFilter = AtomicReference(FilterSearch())

    override fun fetchSearchFilter(): FilterSearch = localFilter.get()

    override fun updateSearchFilter(filterSearch: FilterSearch) {
        localFilter.set(filterSearch)
    }

    override fun clearSearchFilter() {
        localFilter.set(FilterSearch())
    }

    override suspend fun getAnimes(input: String?, page: Int)= resultWrapper.wrap {
        val filter = localFilter.get()
        searchAnimeRemoteSource.getAnimes(input, filter, page).map { it.mapToDomain() }
    }

    override suspend fun getQuickSearchAnimes() = resultWrapper.wrap {
        val filter = localFilter.get()
        searchAnimeRemoteSource.getQuickSearchAnimes(filter).map { it.mapToDomain() }
    }

    override suspend fun addToSkipList(animeId: String) = resultWrapper.wrap {
        searchAnimeRemoteSource.addToSkipList(animeId)
    }

    override suspend fun deleteFromSkipList(animeId: String) = resultWrapper.wrap {
        searchAnimeRemoteSource.deleteFromSkipList(animeId)
    }
}
