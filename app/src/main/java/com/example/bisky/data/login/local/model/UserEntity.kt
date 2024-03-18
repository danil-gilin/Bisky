package com.example.bisky.data.login.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val _id: String,
    val email: String,
    val image: String?,
    val name: String?,
    val password: String?,
    val refreshToken: String?,
    val role: String?,
    val username: String
)
