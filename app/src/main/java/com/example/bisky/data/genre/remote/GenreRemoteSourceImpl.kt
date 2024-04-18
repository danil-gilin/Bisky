package com.example.bisky.data.genre.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GenreAnimeQuery
import com.example.GetAllGenreWithSimpleInfoQuery
import com.example.GetGenresQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.genre.remote.GenreRemoteSource
import com.example.type.FilterAnimeQuery
import com.example.type.GeneralAnimeQuery
import com.example.type.GeneralGenreQuery
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider
) : GenreRemoteSource {

    override suspend fun getGenres(page: Int) = withContext(dispatchersProvider.io) {
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

    override suspend fun getAnimesGenre(genreId: String, page: Int) = withContext(dispatchersProvider.io) {
        apolloClient.query(
            GenreAnimeQuery(
                GeneralAnimeQuery(
                    count = 30.toOptional(),
                    filter = FilterAnimeQuery(
                        genres_ID_ONLY = listOf<String>(genreId).toOptional()
                    ).toOptional(),
                    page = page.toOptional()
                )
            )
        )
            .execute()
            .data
            ?.getAnimes ?: emptyList()
    }

    override suspend fun getAllGenre() = withContext(dispatchersProvider.io) {
        apolloClient.query(GetAllGenreWithSimpleInfoQuery(
            GeneralGenreQuery(
                count = 120.toOptional()
            )
        ))
            .execute()
            .data
            ?.getGenres ?: emptyList()
    }
}
