package com.example.bisky.data.login.mapper

import com.example.bisky.data.login.local.model.UserEntity
import com.example.bisky.data.login.remote.model.UserResponse
import com.example.bisky.domain.repository.login.model.User

fun UserResponse.mapToEntity() = UserEntity(
    _id = _id,
    email = email,
    image = image,
    name = name,
    password = password,
    refreshToken = refreshToken,
    role = role,
    username = username
)

fun UserEntity.mapToDomain() = User(
    _id = _id,
    email = email,
    image = image,
    name = name,
    password = password,
    refreshToken = refreshToken,
    role = role,
    username = username
)
