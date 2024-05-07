package com.example.bisky.domain.repository.searchanime

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.searchanime.model.AnimeQuickSearch
import com.example.bisky.domain.repository.searchanime.model.AnimeSearch
import com.example.bisky.domain.repository.searchanime.model.FilterSearch

interface SearchAnimeRepository {
    suspend fun getAnimes(input: String?, page: Int): Result<List<AnimeSearch>, Throwable>
    suspend fun getQuickSearchAnimes(): Result<List<AnimeQuickSearch>, Throwable>
    fun fetchSearchFilter(): FilterSearch
    fun updateSearchFilter(filterSearch: FilterSearch)
    fun clearSearchFilter()
    suspend fun addToSkipList(animeId: String): Result<Boolean, Throwable>
    suspend fun deleteFromSkipList(animeId: String): Result<Boolean, Throwable>
}