package com.example.moviewatcher.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviewatcher.model.Video

@Dao
interface IAppDao {

    @Query("SELECT * FROM Video")
    fun getAllVideoInfos(): LiveData<List<Video>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideoInfos(repositoryData: Video)

    @Query("DELETE FROM Video")
    fun deleteAllVideoInfo()
}

