package com.example.moviewatcher.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "VideoInfo")
data class VideoInfo(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")
    val id:Int =0 ,
    @ColumnInfo(name = "description")
    val description: String?,
    @TypeConverters(SourcesConverter::class)
    val sources: List<String>?,
    @ColumnInfo(name = "subtitle")
    val subtitle: String?,
    @ColumnInfo(name = "thumb")
    val thumb: String?,
    @ColumnInfo(name = "title")
    val title: String?
)