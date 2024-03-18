package com.example.bisky.data.room

import android.content.Context
import androidx.room.Room
import com.example.bisky.data.room.converter.ListGenreTypeConverter
import com.example.bisky.data.room.converter.ListStringTypeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseDIModule {
    @Singleton
    @Provides
    fun provideListStringTypeConverter(moshi: Moshi) = ListStringTypeConverter(moshi)

    @Singleton
    @Provides
    fun provideListGenreTypeConverter(moshi: Moshi) = ListGenreTypeConverter(moshi)

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        listStringTypeConverter: ListStringTypeConverter,
        listGenreTypeConverter: ListGenreTypeConverter
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "bisky.db")
        .addTypeConverter(listStringTypeConverter)
        .addTypeConverter(listGenreTypeConverter)
        .fallbackToDestructiveMigration()
        .build()
}
