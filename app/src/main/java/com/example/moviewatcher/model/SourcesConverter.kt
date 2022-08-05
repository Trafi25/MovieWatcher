package com.example.moviewatcher.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import org.json.JSONObject
import java.util.*
import java.util.stream.Collectors

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