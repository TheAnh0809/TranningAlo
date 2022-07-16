package com.example.tranningalo1.viewmodel

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tranningalo1.CallAPI.MainRepository
import com.example.tranningalo1.CallAPI.RetrofitService
import com.example.tranningalo1.model.WallRespone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream


class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val wallList = MutableLiveData<WallRespone>()
    val errorMessage = MutableLiveData<String>()

    fun getAllWall() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev.wallpapers-free.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var call = retrofit.create(RetrofitService::class.java)
        call.getAllWall().enqueue(object : Callback<WallRespone> {
            override fun onResponse(call: Call<WallRespone>, response: Response<WallRespone>) {
                Log.v("WallRespone", "OK : $response")
                wallList.postValue(response.body() as WallRespone?)
            }

            override fun onFailure(call: Call<WallRespone>, t: Throwable) {
                Log.v("WallRespone", "false : $t")
                errorMessage.postValue(t.message)
            }
        })
    }
    fun encodeTobase64(image: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        val imageEncoded: String = Base64.encodeToString(b, Base64.DEFAULT)
        Log.d("Image Log:", imageEncoded)
        return imageEncoded
    }
}