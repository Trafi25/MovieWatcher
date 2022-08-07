package com.example.moviewatcher.network


import com.example.moviewatcher.model.ListInfo
import retrofit2.Response
import retrofit2.http.GET

interface IRetrofitServices {

    @GET("Trafi25/links/main/links")
    suspend fun getDataFromAPI(): Response<ListInfo>

/*    @GET("Trafi25/links/main/links")
    fun getDataFromAPI(): Call<ListInfo>*/
}