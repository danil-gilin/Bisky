package com.example.bisky.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bisky.data.room.converter.ListGenreTypeConverter
import com.example.bisky.data.room.converter.ListStringTypeConverter
import com.example.bisky.data.seasonanime.local.SeasonAnimeDao
import com.example.bisky.data.seasonanime.local.model.SeasonAnimeEntity

@Database(
    entities = [
        SeasonAnimeEntity::class
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(
    ListStringTypeConverter::class,
    ListGenreTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun seasonAnimeDao(): SeasonAnimeDao
}
