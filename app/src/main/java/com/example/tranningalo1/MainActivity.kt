package com.example.tranningalo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tranningalo1.CallAPI.MainRepository
import com.example.tranningalo1.CallAPI.RetrofitService
import com.example.tranningalo1.databinding.ActivityMainBinding
import com.example.tranningalo1.viewmodel.MainViewModel
import com.example.tranningalo1.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.wallList.observe(this, Observer {
            println(it.size)
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getAllMovies()
    }
}