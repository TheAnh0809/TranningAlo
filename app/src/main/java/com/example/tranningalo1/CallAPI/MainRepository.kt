package com.example.tranningalo1.CallAPI

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllWall() = retrofitService.getAllWall()
}