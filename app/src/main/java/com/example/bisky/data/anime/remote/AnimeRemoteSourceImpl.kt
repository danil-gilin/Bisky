package com.example.bisky.data.anime.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GetAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.anime.remote.model.RatingUpdateRequest
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.anime.remote.AnimeRemoteSource
import com.example.type.FilterAnimeQuery
import com.example.type.GeneralAnimeQuery
import javax.inject.Inject
import kotlinx.coroutines.withContext

class AnimeRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val animeAPi: AnimeApi,
    private val dispatchersProvider: DispatchersProvider
) : AnimeRemoteSource {
    override suspend fun getAnime(id: String) =
        withContext(dispatchersProvider.io) {
            val filter = GeneralAnimeQuery(
                count = 1.toOptional(),
                filter = FilterAnimeQuery(
                    _id_ID = listOf(id).toOptional()
                ).toOptional()
            )
            apolloClient.query(GetAnimeQuery(filter))
                .execute()
                .data
                ?.getAnimes
        }

    override suspend fun updateRatingAnime(rating: Int, animeId: String) =
        animeAPi.updateAnimeRating(RatingUpdateRequest(rating), animeId)
}
