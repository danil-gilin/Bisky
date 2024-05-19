package com.example.bisky.domain.repository.login

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.login.model.User

interface LoginRepository {

    suspend fun checkSigIn(): Result<User?, Throwable>
    suspend fun sigUp(name: String, password: String, email: String): Result<Unit, Throwable>
    suspend fun sigIn(name: String, password: String): Result<Unit, Throwable>
    suspend fun sigOut(): Result<Boolean, Throwable>
    suspend fun fetchUser(): User?
    suspend fun refreshToken(): Result<Unit, Throwable>
}
