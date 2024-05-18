package com.example.bisky.data.newseries.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GetAnimeQuery
import com.example.GetNewSeriesAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.newseries.NewSeriesAnimeRemoteSource
import com.example.type.FilterAnimeQuery
import com.example.type.GeneralAnimeQuery
import com.example.type.KindEnum
import com.example.type.RatingEnum
import com.example.type.SortAnimeQuery
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewSeriesAnimeRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider
) : NewSeriesAnimeRemoteSource {

    override suspend fun getNewSeriesAnime() =
        withContext(dispatchersProvider.io)
        {
            val filter = GeneralAnimeQuery(
                count = 40.toOptional(),
                isPaginationOff = true.toOptional(),
                sort = SortAnimeQuery(
                    episodes_lastEpisodeAiredDate = true.toOptional()
                ).toOptional(),
                filterExclude = FilterAnimeQuery(
                    kind = listOf(
                        KindEnum.special,
                        KindEnum.music,
                        KindEnum.tv_special,
                        KindEnum.none,
                        KindEnum.pv,
                        KindEnum.cm
                    ).toOptional(),
                    rating = listOf(
                        RatingEnum.rx,
                        RatingEnum.none,
                        RatingEnum.g,
                        RatingEnum.pg,
                    ).toOptional(),
                    genres_ID_ONLY = listOf("664125f3fab099cd5adf415f").toOptional()
                ).toOptional()
            )
            apolloClient.query(GetNewSeriesAnimeQuery(filter))
                .execute()
                .data
                ?.getAnimes ?: emptyList()
        }
}