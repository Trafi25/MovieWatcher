package com.example.moviewatcher.network

import com.example.moviewatcher.model.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitServices {

    @GET("/Trafi25/links/main/links")
    fun getRoot(): Call<Root>
}