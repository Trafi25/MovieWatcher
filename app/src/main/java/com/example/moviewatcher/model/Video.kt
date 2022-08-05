package com.example.moviewatcher.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Entity(tableName = "links")
data class Video(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")
    val id:Int =0 ,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "sources")
    val sources: List<String>?,
    @ColumnInfo(name = "subtitle")
    val subtitle: String?,
    @ColumnInfo(name = "thumb")
    val thumb: String?,
    @ColumnInfo(name = "title")
    val title: String?
)