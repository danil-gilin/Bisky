package com.example.bisky.data.seasonanime.local

import androidx.room.Dao
import androidx.room.Query
import com.example.bisky.data.room.BaseDao
import com.example.bisky.data.seasonanime.local.model.SeasonAnimeEntity

@Dao
interface SeasonAnimeDao : BaseDao<SeasonAnimeEntity> {

    @Query("SELECT * FROM season_anime")
    suspend fun fetchListSeasonAnime(): List<SeasonAnimeEntity>
}
