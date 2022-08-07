package com.example.moviewatcher.model

import androidx.room.TypeConverter

class SourcesConverter {

    @TypeConverter
    fun fromSources(sources: List<String?>): String? {
        return sources[0]
    }

    @TypeConverter
    fun toSources(data: String): List<String> {
        return listOf(*data.split(",").toTypedArray())
    }

}