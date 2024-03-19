package com.example.bisky.domain.repository.login.remote

import com.example.bisky.data.login.remote.model.TokenResponse

interface LoginRemoteSource {

    suspend fun sigIn(name: String, password: String): TokenResponse
    suspend fun sigUp(name: String, password: String, email: String): TokenResponse
    suspend fun checkSigIn() // : UserResponse
    suspend fun refreshToken(): TokenResponse
}
