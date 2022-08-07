package com.example.moviewatcher.network


import com.example.moviewatcher.Utils.Constants
import com.example.moviewatcher.model.ListInfo
import retrofit2.Response
import retrofit2.http.GET

interface IRetrofitServices {

    @GET(Constants.END_URL)
    suspend fun getDataFromAPI(): Response<ListInfo>

}