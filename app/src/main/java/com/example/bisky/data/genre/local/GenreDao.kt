package com.example.bisky.data.genre.local

import androidx.room.Dao
import androidx.room.Query
import com.example.bisky.data.genre.local.model.GenreEntity
import com.example.bisky.data.room.BaseDao

@Dao
interface GenreDao : BaseDao<GenreEntity> {

    @Query("SELECT * FROM genre")
    suspend fun getGenres() : List<GenreEntity>
}