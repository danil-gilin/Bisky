package com.example.bisky.data.archive.local

import androidx.room.Dao
import androidx.room.Query
import com.example.bisky.data.room.BaseDao
import com.example.bisky.domain.repository.archive.local.model.AddAnime
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import kotlinx.coroutines.flow.Flow

@Dao
interface CompleteCollectionDao: BaseDao<CompleteAnime> {
    @Query("DELETE FROM complete_collection_anime WHERE id =:id")
    suspend fun deleteAnime(id: String)

    @Query("SELECT * FROM complete_collection_anime")
    suspend fun fetchAll(): List<CompleteAnime>

    @Query("SELECT * FROM complete_collection_anime")
    fun subscribe(): Flow<List<CompleteAnime>>
}