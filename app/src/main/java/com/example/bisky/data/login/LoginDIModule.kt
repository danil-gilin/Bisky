package com.example.bisky.data.login

import android.content.SharedPreferences
import com.example.bisky.data.login.local.TokenPreference
import com.example.bisky.data.login.remote.LoginApi
import com.example.bisky.data.login.remote.LoginRemoteSourceImpl
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LoginDIModule {
    @Singleton
    @Provides
    fun provideLoginAPi(retrofit: Retrofit) = retrofit.create(LoginApi::class.java)

    @Singleton
    @Provides
    fun provideLoginRemoteSource(loginApi: LoginApi) = LoginRemoteSourceImpl(
        loginApi
    )

    @Singleton
    @Provides
    fun provideLoginLocalSource(sharedPreferences: SharedPreferences) = TokenPreference(
        sharedPreferences
    )

    @Singleton
    @Provides
    fun provideLoginRepository(
        loginRemoteSource: LoginRemoteSourceImpl,
        localSourceImpl: TokenPreference,
        resultWrapper: ResultWrapper
    ) = LoginRepositoryImpl(
        loginRemoteSource = loginRemoteSource,
        tokenPreference = localSourceImpl,
        resultWrapper = resultWrapper
    )
}