package com.example.moviewatcher.Utils

import android.provider.MediaStore
import com.example.moviewatcher.model.Video
import java.util.*

class Common {

    companion object {
        val videoList = mutableListOf<String>()

        fun setVideos(videosList: List<Video>): MutableList<String> {
            for(item in videosList){
                videoList.add(item.sources?.get(0).toString())
            }
            return videoList
        }
    }
}

