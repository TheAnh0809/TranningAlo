package com.example.tranningalo1.CallAPI

import com.example.tranningalo1.model.WallRespone
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitService {
    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://dev.wallpapers-free.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
    @Headers("language: vi", "country:VN")
    @GET("/api/wallpapers?imagePageNumber=1&videoPageNumber=1")
    fun getAllWall() : Call<WallRespone>
}