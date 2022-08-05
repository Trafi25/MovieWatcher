package com.example.moviewatcher.dInject

import android.app.Application
import com.example.moviewatcher.db.IAppDao
import com.example.moviewatcher.db.VideoDB
import com.example.moviewatcher.network.IRetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppDataInstance(application: Application) {
    private lateinit var retrofit: Retrofit
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var application: Application

    init {
        this.application = application!!
    }

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): VideoDB {
        return VideoDB.getVideoDBInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(videoDB: VideoDB): IAppDao {
        return videoDB.getIAppDao()
    }



    @Provides
    @Singleton
    fun getRetrofitService(retrofit: Retrofit): IRetrofitServices{
        return retrofit.create(IRetrofitServices::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(Retrofit::class.java)
    }
}