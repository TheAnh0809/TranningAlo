package com.example.tranningalo1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tranningalo1.CallAPI.MainRepository
import com.example.tranningalo1.model.Wall
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val wallList = MutableLiveData<ArrayList<Wall>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllWall()
        response.enqueue(object : retrofit2.Callback<List<Wall>> {

            override fun onResponse(call: Call<List<Wall>>, response: Response<List<Wall>>) {
                wallList.postValue(response.body() as ArrayList<Wall>?)
            }

            override fun onFailure(call: Call<List<Wall>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}