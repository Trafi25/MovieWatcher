package com.example.moviewatcher.network

import androidx.lifecycle.LiveData
import com.example.moviewatcher.db.IAppDao
import com.example.moviewatcher.model.ListInfo
import com.example.moviewatcher.model.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class RetrofitCalls @Inject constructor(
    private val IRetrofitServices: IRetrofitServices,
    private val appDao: IAppDao
) {

    fun getAllVideos(): LiveData<List<Video>> {
        return appDao.getAllVideoInfos()
    }

    private fun insertVideo(repositoryData: Video) {
        CoroutineScope(Dispatchers.IO).launch {
            appDao.insertVideoInfos(repositoryData)
        }
    }

    private fun deleteAllVideoInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            appDao.deleteAllVideoInfo()
        }
    }

    fun getVideoList() {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<ListInfo> = IRetrofitServices.getDataFromAPI()
            if (response.isSuccessful) {
                deleteAllVideoInfo()
                val rootInfo: ListInfo? = response.body()
                if (rootInfo != null) {
                    val videoList = rootInfo.videos as ArrayList<Video>
                    for (result: Video in videoList) {
                        //Log.d("this_out", result.title.toString())
                        insertVideo(result)
                    }
                }
            } else {
                //Log.d("watas", "i hate life")
            }
        }
    }
}