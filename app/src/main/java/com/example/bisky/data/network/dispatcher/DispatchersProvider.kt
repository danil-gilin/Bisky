package com.example.bisky.data.network.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
    val io: CoroutineDispatcher
}
