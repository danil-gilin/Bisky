package com.example.bisky.data.archive.local

import androidx.room.Dao
import androidx.room.Query
import com.example.bisky.data.room.BaseDao
import com.example.bisky.domain.repository.archive.local.model.AddAnime
import kotlinx.coroutines.flow.Flow

@Dao
interface AddCollectionDao: BaseDao<AddAnime> {

    @Query("DELETE FROM add_collection_anime WHERE id =:id")
    suspend fun deleteAnime(id: String)

    @Query("SELECT * FROM add_collection_anime")
    suspend fun fetchAll(): List<AddAnime>

    @Query("SELECT * FROM add_collection_anime")
    fun subscribe(): Flow<List<AddAnime>>
}