package com.example.moviewatcher.db

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.moviewatcher.model.Video

interface IAppDao {

    @Query("SELECT * FROM links")
    fun getAllLinks(): LiveData<List<Video>>

}