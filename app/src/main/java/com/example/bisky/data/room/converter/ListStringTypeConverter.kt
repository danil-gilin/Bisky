package com.example.bisky.data.room.converter

import androidx.room.ProvidedTypeConverter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class ListStringTypeConverter(moshi: Moshi) :
    ListTypeConverter<String>(moshi, String::class.java)
