package com.example.bisky.data.room.converter

import androidx.room.ProvidedTypeConverter
import com.example.bisky.data.seasonanime.local.model.GenreEntity
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class ListGenreTypeConverter(moshi: Moshi) :
    ListTypeConverter<GenreEntity>(moshi, GenreEntity::class.java)
