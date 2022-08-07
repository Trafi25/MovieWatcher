package com.example.moviewatcher.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviewatcher.model.SourcesConverter
import com.example.moviewatcher.model.Video

@Database(entities = [Video::class], version = 1, exportSchema = false)
@TypeConverters(SourcesConverter::class)
abstract class VideoDB : RoomDatabase() {

    abstract fun getIAppDao(): IAppDao

    companion object {
        private var dbInstance: VideoDB? = null
        fun getVideoDBInstance(context: Context): VideoDB {
            if (dbInstance == null) {
                dbInstance =
                    Room.databaseBuilder(context.applicationContext,
                        VideoDB::class.java, "APP_DB")
                        .allowMainThreadQueries()
                        .build()
            }
            return dbInstance!!
        }
    }
}