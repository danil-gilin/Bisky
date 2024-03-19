package com.example.bisky.data.login.mapper

import com.example.bisky.data.login.local.model.UserEntity
import com.example.bisky.data.login.remote.model.UserResponse
import com.example.bisky.domain.repository.login.model.User

fun UserResponse.mapToEntity() = UserEntity(
    username = username,
    email = email,
    avatar = avatar,
    lastOnlineDate = lastOnlineDate,
    role = role
)

fun UserEntity.mapToDomain() = User(
    username = username,
    email = email,
    avatar = avatar,
    lastOnlineDate = lastOnlineDate,
    role = role
)
