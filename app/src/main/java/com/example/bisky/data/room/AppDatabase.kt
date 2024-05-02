package com.example.bisky.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bisky.data.archive.local.AddCollectionDao
import com.example.bisky.data.archive.local.CompleteCollectionDao
import com.example.bisky.data.archive.local.WatchCollectionDao
import com.example.bisky.data.genre.local.GenreDao
import com.example.bisky.data.genre.local.model.GenreEntity
import com.example.bisky.data.login.local.LoginDao
import com.example.bisky.data.login.local.model.UserEntity
import com.example.bisky.data.room.converter.ListGenreTypeConverter
import com.example.bisky.data.room.converter.ListStringTypeConverter
import com.example.bisky.data.seasonanime.local.SeasonAnimeDao
import com.example.bisky.data.seasonanime.local.model.SeasonAnimeEntity
import com.example.bisky.domain.repository.archive.local.model.AddAnime
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import com.example.bisky.domain.repository.archive.local.model.WatchAnime

@Database(
    entities = [
        SeasonAnimeEntity::class,
        UserEntity::class,
        GenreEntity::class,
        AddAnime::class,
        WatchAnime::class,
        CompleteAnime::class
    ],
    version = 3,
    exportSchema = true
)

@TypeConverters(
    ListStringTypeConverter::class,
    ListGenreTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun seasonAnimeDao(): SeasonAnimeDao
    abstract fun loginDao(): LoginDao
    abstract fun genreDao(): GenreDao
    abstract fun addCollectionDao(): AddCollectionDao
    abstract fun watchCollectionDao(): WatchCollectionDao
    abstract fun completeCollectionDao(): CompleteCollectionDao
}
