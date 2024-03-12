package com.example.bisky.data.seasonanime

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.seasonanime.remote.ApolloSeasonAnimeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SeasonAnimeDIModule {

    @Singleton
    @Provides
    fun provideSeasonAnimeRepository(seasonAnimeClient: ApolloSeasonAnimeClient) =
        SeasonAnimeRepository(seasonAnimeClient)

    @Singleton
    @Provides
    fun provideApolloSeasonAnimeClient(apolloClient: ApolloClient): ApolloSeasonAnimeClient =
        ApolloSeasonAnimeClient(apolloClient)
}
