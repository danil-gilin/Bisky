package com.example.bisky.data.archive.local

import androidx.room.Dao
import androidx.room.Query
import com.example.bisky.data.room.BaseDao
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import com.example.bisky.domain.repository.archive.local.model.WatchAnime
import kotlinx.coroutines.flow.Flow


@Dao
interface WatchCollectionDao : BaseDao<WatchAnime> {
    @Query("DELETE FROM watch_collection_anime WHERE id =:id")
    suspend fun deleteAnime(id: String)

    @Query("SELECT * FROM watch_collection_anime")
    suspend fun fetchAll(): List<WatchAnime>

    @Query("SELECT * FROM watch_collection_anime")
    fun subscribe(): Flow<List<WatchAnime>>
}