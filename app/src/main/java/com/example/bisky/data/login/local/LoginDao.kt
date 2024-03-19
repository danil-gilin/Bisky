package com.example.bisky.data.login.local

import androidx.room.Dao
import androidx.room.Query
import com.example.bisky.data.login.local.model.UserEntity
import com.example.bisky.data.room.BaseDao

@Dao
interface LoginDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM user")
    suspend fun fetchUser(): UserEntity?

    @Query("DELETE FROM user")
    suspend fun clearUser()
}
