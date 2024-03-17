package com.example.bisky.data.login.remote

import com.example.bisky.data.login.remote.model.SigInParams
import com.example.bisky.data.login.remote.model.SigUpParams

class LoginRemoteSourceImpl(
    private val loginApi: LoginApi
) {

    suspend fun sigIn(name: String, password: String) =
        loginApi.sigIn(
            SigInParams(
                username = name,
                password = password
            )
        )

    suspend fun sigUp(name: String, password: String, email: String) =
        loginApi.sigUp(
            SigUpParams(
                username = name,
                password = password,
                email = email
            )
        )


    suspend fun checkSigIn() = loginApi.checkSigIn()
    suspend fun refreshToken() = loginApi.refreshToken()
}
