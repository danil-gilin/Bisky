package com.example.bisky.data.login.remote

import com.example.bisky.data.login.remote.model.SigInParams

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
}
