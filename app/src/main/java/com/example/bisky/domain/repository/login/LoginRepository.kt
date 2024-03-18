package com.example.bisky.domain.repository.login

import com.example.bisky.data.network.resultwrapper.Result

interface LoginRepository {

    suspend fun checkSigIn(): Result<Unit, Throwable>
    suspend fun sigUp(name: String, password: String, email: String): Result<Unit, Throwable>
    suspend fun sigIn(name: String, password: String): Result<Unit, Throwable>
}
