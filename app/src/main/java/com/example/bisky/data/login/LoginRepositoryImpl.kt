package com.example.bisky.data.login

import com.example.bisky.data.login.local.TokenPreference
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.login.LoginRepository
import com.example.bisky.domain.repository.login.remote.LoginRemoteSource
import retrofit2.HttpException

class LoginRepositoryImpl(
    private val loginRemoteSource: LoginRemoteSource,
    private val tokenPreference: TokenPreference,
    private val resultWrapper: ResultWrapper
) : LoginRepository {

    override suspend fun sigIn(name: String, password: String) = resultWrapper.wrap {
        val result = loginRemoteSource.sigIn(name, password)
        tokenPreference.saveToken(result.accessToken, result.refreshToken)
    }

    override suspend fun sigUp(name: String, password: String, email: String) = resultWrapper.wrap {
        val result = loginRemoteSource.sigUp(name, password, email)
        tokenPreference.saveToken(result.accessToken, result.refreshToken)
    }

    override suspend fun checkSigIn() = resultWrapper.wrap {
        try {
            loginRemoteSource.checkSigIn()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                tokenPreference.clearAccessToken()
                val result = loginRemoteSource.refreshToken()
                tokenPreference.saveToken(result.accessToken, result.refreshToken)
            } else {
                throw e
            }
        }
        Unit
    }
}
