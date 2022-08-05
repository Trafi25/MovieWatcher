package com.example.moviewatcher.network

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.moviewatcher.db.IAppDao
import com.example.moviewatcher.model.Root
import com.example.moviewatcher.model.VideoInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitCalls @Inject constructor(
    private val retrofitService : IRetrofitServices,
    private val appDao : IAppDao) {

    fun getAllVideoInfos():LiveData<List<VideoInfo>>{
        return appDao.getAllVideoInfos()
    }
    fun insertVideo(videoInfo: VideoInfo){
        appDao.insertVideoInfo(videoInfo)
    }


    fun makeCall(){
        val call: Call<Root> = retrofitService.getRoot()
        call?.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                appDao.deleteAllVideoInfo()
                if(response.isSuccessful){
                    Log.d("output: ", response.body().toString())
                    response.body()?.videoInfos?.forEach{
                        insertVideo(it)
                    }
                }
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

}