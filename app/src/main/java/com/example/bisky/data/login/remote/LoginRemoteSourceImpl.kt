package com.example.bisky.data.login.remote

import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.login.model.SigInParams
import com.example.bisky.domain.repository.login.model.SigUpParams
import com.example.bisky.domain.repository.login.remote.LoginRemoteSource
import kotlinx.coroutines.withContext

class LoginRemoteSourceImpl(
    private val loginApi: LoginApi,
    private val dispatchersProvider: DispatchersProvider
) : LoginRemoteSource {

    override suspend fun sigIn(name: String, password: String) =
        withContext(dispatchersProvider.io) {
            loginApi.sigIn(
                SigInParams(
                    username = name,
                    password = password
                )
            )
        }

    override suspend fun sigOut() = withContext(dispatchersProvider.io) {
        loginApi.sigOut()
    }

    override suspend fun sigUp(name: String, password: String, email: String) =
        withContext(dispatchersProvider.io) {
            loginApi.sigUp(
                SigUpParams(
                    username = name,
                    password = password,
                    email = email
                )
            )
        }

    override suspend fun checkSigIn() =
        withContext(dispatchersProvider.io) { loginApi.checkSigIn() }

    override suspend fun refreshToken() =
        withContext(dispatchersProvider.io) { loginApi.refreshToken() }
}
