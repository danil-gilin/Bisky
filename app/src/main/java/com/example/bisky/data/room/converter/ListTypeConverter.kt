package com.example.bisky.data.room.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

abstract class ListTypeConverter<T> constructor(
    private val moshi: Moshi,
    private val clazz: Class<T>
) {
    private val listAdapter: JsonAdapter<List<T>> by lazy {
        val listType = Types.newParameterizedType(List::class.java, clazz)
        moshi.adapter(listType)
    }

    @TypeConverter
    fun stringToItems(string: String?): List<T>? {
        return string?.let { value ->
            val result = listAdapter.fromJson(value)
            result
        }
    }

    @TypeConverter
    fun itemsToString(items: List<T>?): String? {
        return items?.let {
            listAdapter.toJson(it)
        }
    }
}
