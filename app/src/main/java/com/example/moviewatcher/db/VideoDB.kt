package com.example.moviewatcher.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviewatcher.model.SourcesConverter
import com.example.moviewatcher.model.VideoInfo

@Database(entities = [VideoInfo::class], version = 1, exportSchema = false)
@TypeConverters(SourcesConverter::class)
abstract class VideoDB: RoomDatabase() {

    abstract fun getIAppDao(): IAppDao

    companion object{
        private var DB_INSTANCE: VideoDB? = null

        fun getVideoDBInstance(context:Context): VideoDB{
            if (DB_INSTANCE== null){
                DB_INSTANCE= Room.databaseBuilder(
                    context.applicationContext,
                    VideoDB::class.java
                    ,"APP_VIDEO_DB")
                    .allowMainThreadQueries().build()
            }
            return DB_INSTANCE!!
        }
    }

}