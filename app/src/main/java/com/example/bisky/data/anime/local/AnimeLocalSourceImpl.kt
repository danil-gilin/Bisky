package com.example.bisky.data.anime.local

import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.anime.local.AnimeLocalSource
import javax.inject.Inject

class AnimeLocalSourceImpl @Inject constructor(
    private val dispatchersProvider: DispatchersProvider
) : AnimeLocalSource
