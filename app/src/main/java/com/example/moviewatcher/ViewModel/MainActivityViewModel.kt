package com.example.moviewatcher.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviewatcher.model.Video
import com.example.moviewatcher.network.RetrofitCalls
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: RetrofitCalls
) : ViewModel() {

    fun getAllVideoList(): LiveData<List<Video>> {
        return repository.getAllVideos()
    }
    fun makeApiRequest() {
        repository.getVideoList()
    }
}
