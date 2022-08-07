package com.example.moviewatcher.network

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.moviewatcher.db.IAppDao
import com.example.moviewatcher.model.ListInfo
import com.example.moviewatcher.model.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class RetrofitCalls @Inject constructor(private val IRetrofitServices: IRetrofitServices,
                                        private val appDao: IAppDao
) {

    fun getAllVideos(): LiveData<List<Video>> {
        return appDao.getAllVideoInfos()
    }

    fun insertVideo(repositoryData: Video) {
        appDao.insertVideoInfos(repositoryData)
    }
/*    fun makeApiCall() {
        val call: Call<ListInfo> = retroServiceInterface.getDataFromAPI()
        call?.enqueue(object : Callback<ListInfo>{
            override fun onResponse(
                call: Call<ListInfo>,
                response: Response<ListInfo>
            ) {
                appDao.deleteAllVideoInfo()
                var rootInfo: ListInfo? = response.body()
                if (rootInfo != null) {
                    var frg = rootInfo.videos as ArrayList<Video>
                    for (result: Video in frg) {
                        var fsd = result
                        insertRecord(result)

                    }
                }
            }
            override fun onFailure(call: Call<ListInfo>, t: Throwable) {
                Log.d("watas", "i hate life")
            }
        })
    }*/

     fun getVideoList() {
        CoroutineScope(Dispatchers.IO).launch  {
        val response: Response<ListInfo> = IRetrofitServices.getDataFromAPI()
        if(response.isSuccessful){
            appDao.deleteAllVideoInfo()
            var rootInfo: ListInfo? = response.body()
            if (rootInfo != null) {
                var frg = rootInfo.videos as ArrayList<Video>
                for (result: Video in frg) {
                    insertVideo(result)
                }
                }
        }else{
            Log.d("watas", "i hate life")
        }


        }
    }
}