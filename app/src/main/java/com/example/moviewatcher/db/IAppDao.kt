package com.example.moviewatcher.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviewatcher.model.VideoInfo

@Dao
interface IAppDao {

    @Query("SELECT * FROM VideoInfo")
    fun getAllVideoInfos(): LiveData<List<VideoInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideoInfo(videoInfo: VideoInfo)

    @Query("DELETE FROM VideoInfo")
    fun deleteAllVideoInfo()
}