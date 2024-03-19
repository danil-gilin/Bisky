package com.example.bisky.data.login.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val username: String,
    val email: String,
    val avatar: String?,
    val lastOnlineDate: String?,
    val role: String?
)
