package com.example.moviewatcher.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviewatcher.model.VideoInfo
import com.example.moviewatcher.network.RetrofitCalls
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val videoInfo: RetrofitCalls): ViewModel() {

        fun getVideoInfosList(): LiveData<List<VideoInfo>>{
            return videoInfo.getAllVideoInfos()
        }

        fun makeApiCall(){
        videoInfo.makeCall()
    }

}