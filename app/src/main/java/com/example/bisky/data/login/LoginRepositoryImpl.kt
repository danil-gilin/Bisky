package com.example.bisky.data.login

import com.example.bisky.data.login.local.TokenPreference
import com.example.bisky.data.login.mapper.mapToDomain
import com.example.bisky.data.login.mapper.mapToEntity
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.login.LoginRepository
import com.example.bisky.domain.repository.login.local.LoginLocalSource
import com.example.bisky.domain.repository.login.remote.LoginRemoteSource
import retrofit2.HttpException

class LoginRepositoryImpl(
    private val loginRemoteSource: LoginRemoteSource,
    private val tokenPreference: TokenPreference,
    private val loginLocalSource: LoginLocalSource,
    private val resultWrapper: ResultWrapper
) : LoginRepository {

    override suspend fun sigIn(name: String, password: String) = resultWrapper.wrap {
        val result = loginRemoteSource.sigIn(name, password)
        tokenPreference.saveToken(result.accessToken, result.refreshToken)
        val user = loginRemoteSource.checkSigIn()
        loginLocalSource.updateUser(user.mapToEntity())
    }

    override suspend fun fetchUser() = loginLocalSource.fetchUser()?.mapToDomain()

    override suspend fun sigUp(name: String, password: String, email: String) = resultWrapper.wrap {
        val result = loginRemoteSource.sigUp(name, password, email)
        tokenPreference.saveToken(result.accessToken, result.refreshToken)
        val user = loginRemoteSource.checkSigIn()
        loginLocalSource.updateUser(user.mapToEntity())
    }

    override suspend fun checkSigIn() = resultWrapper.wrap {
        try {
            val user = loginRemoteSource.checkSigIn()
            loginLocalSource.updateUser(user.mapToEntity())
        } catch (e: HttpException) {
            if (e.code() == 401) {
                tokenPreference.clearAccessToken()
                val refreshResult = loginRemoteSource.refreshToken()
                tokenPreference.saveToken(refreshResult.accessToken, refreshResult.refreshToken)
                val user = loginRemoteSource.checkSigIn()
                loginLocalSource.updateUser(user.mapToEntity())
            } else {
                throw e
            }
        }
        loginLocalSource.fetchUser()?.mapToDomain()
    }
}
