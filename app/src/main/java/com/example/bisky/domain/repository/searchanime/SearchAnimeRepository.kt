package com.example.bisky.domain.repository.searchanime

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.searchanime.model.AnimeSearch
import com.example.bisky.domain.repository.searchanime.model.FilterSearch

interface SearchAnimeRepository {
    suspend fun getAnimes(input: String): Result<List<AnimeSearch>, Throwable>
    fun fetchSearchFilter(): FilterSearch
    fun updateSearchFilter(filterSearch: FilterSearch)
    fun clearSearchFilter()
}