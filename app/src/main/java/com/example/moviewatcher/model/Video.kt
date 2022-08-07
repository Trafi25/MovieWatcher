package com.example.moviewatcher.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "Video")
data class Video(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "description")
    val description: String?,
    @TypeConverters(SourcesConverter::class) @ColumnInfo(name = "sources")
    val sources: List<String>?,
    @ColumnInfo(name = "subtitle")
    val subtitle: String?,
    @ColumnInfo(name = "thumb")
    val thumb: String,
    @ColumnInfo(name = "title")
    val title: String?
)