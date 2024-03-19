package com.example.bisky.domain.repository.login.local

import com.example.bisky.data.login.local.model.UserEntity

interface LoginLocalSource {
    suspend fun fetchUser(): UserEntity?
    suspend fun updateUser(userEntity: UserEntity)
}
