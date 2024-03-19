package com.example.bisky.data.genre.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GetGenresQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.genre.remote.GenreRemoteSource
import com.example.type.GeneralGenreQuery
import javax.inject.Inject

class GenreRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider
) : GenreRemoteSource {

    override suspend fun getGenres(page: Int) = with(dispatchersProvider.io) {
        apolloClient.query(
            GetGenresQuery(
                GeneralGenreQuery(
                    page = page.toOptional()
                )
            )
        )
            .execute()
            .data
            ?.getGenres ?: emptyList()
    }
}
