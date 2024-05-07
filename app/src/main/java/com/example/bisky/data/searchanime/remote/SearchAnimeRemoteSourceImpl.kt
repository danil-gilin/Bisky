package com.example.bisky.data.searchanime.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GetQuickSearchAnimeQuery
import com.example.GetSearchAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.searchanime.mapper.mapToDto
import com.example.bisky.data.searchanime.remote.model.SkipListResponse
import com.example.bisky.domain.repository.searchanime.model.FilterSearch
import com.example.bisky.domain.repository.searchanime.remote.SearchAnimeRemoteSource
import com.example.bisky.ui.screen.searchpage.filterscreen.model.SortAnimeFilter
import com.example.type.FilterAnimeQuery
import com.example.type.FloatBetweenQuery
import com.example.type.GeneralAnimeQuery
import com.example.type.SortAnimeQuery
import com.example.type.UserFilterQuery
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchAnimeRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider,
    private val searchApi: SearchApi
) : SearchAnimeRemoteSource {
    override suspend fun getAnimes(input: String?, filter: FilterSearch, page: Int) =
        withContext(dispatchersProvider.io) {
            apolloClient.query(
                GetSearchAnimeQuery(
                    GeneralAnimeQuery(
                        count = 30.toOptional(),
                        page = page.toOptional(),
                        searchInput = input.toOptional(),
                        filter = FilterAnimeQuery(
                            genres_ID_ONLY = filter.genres.toOptional(),
                            status = filter.status?.mapToDto().toOptional(),
                            score_averageScore = FloatBetweenQuery(
                                from = filter.scoreRange.start.toDouble().toOptional(),
                                to = filter.scoreRange.endInclusive.toDouble().toOptional()
                            ).toOptional()
                        ).toOptional(),
                        sort = SortAnimeQuery(
                            score_averageScore = (filter.sorted == SortAnimeFilter.RATING).toOptional(),
                            score_count = (filter.sorted == SortAnimeFilter.POPULATION).toOptional()
                        ).toOptional()
                    )
                )
            )
                .execute()
                .data
                ?.getAnimes ?: emptyList()
        }

    override suspend fun getQuickSearchAnimes(
        filter: FilterSearch
    ) = withContext(dispatchersProvider.io) {
        apolloClient.query(
            GetQuickSearchAnimeQuery(
                GeneralAnimeQuery(
                    count = 10.toOptional(),
                    filter = FilterAnimeQuery(
                        genres_ID_ONLY = filter.genres.toOptional(),
                        status = filter.status?.mapToDto().toOptional(),
                        score_averageScore = FloatBetweenQuery(
                            from = filter.scoreRange.start.toDouble().toOptional(),
                            to = filter.scoreRange.endInclusive.toDouble().toOptional()
                        ).toOptional(),
                    ).toOptional(),
                    userFilters = UserFilterQuery(
                        isHiddenAnimeInSkipList = true.toOptional(),
                        isHiddenAnimeInUserList = true.toOptional(),
                    ).toOptional()
                )
            )
        )
            .execute()
            .data
            ?.getAnimes ?: emptyList()
    }

    override suspend fun addToSkipList(animeId: String) = withContext(dispatchersProvider.io) {
        searchApi.addToSkipList(SkipListResponse(animeId))
    }

    override suspend fun deleteFromSkipList(animeId: String) = withContext(dispatchersProvider.io) {
        searchApi.deleteFromSkipList(SkipListResponse(animeId))
    }
}