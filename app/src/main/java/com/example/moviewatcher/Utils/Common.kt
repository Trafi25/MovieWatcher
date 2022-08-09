package com.example.moviewatcher.Utils

import com.example.moviewatcher.model.Video

class Common {

    companion object {
        val videoList = mutableListOf<String>()
        val titleList = mutableListOf<String>()

        fun setVideos(videosList: List<Video>) {
            for (item in videosList) {
                videoList.add(item.sources?.get(0).toString())
                titleList.add(item.title.toString())
            }
        }

    }
}

