package com.example.tranningalo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranningalo1.CallAPI.MainRepository
import com.example.tranningalo1.CallAPI.RetrofitService
import com.example.tranningalo1.databinding.ActivityMainBinding
import com.example.tranningalo1.model.MainAdapter.MainClickInterface
import com.example.tranningalo1.model.Wallpaper
import com.example.tranningalo1.viewmodel.MainViewModel
import com.example.tranningalo1.viewmodel.MyViewModelFactory
import com.example.tranningalo1.model.MainAdapter.RecycleViewAdapter

class MainActivity : AppCompatActivity(), MainClickInterface{
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private lateinit var listData :ArrayList<Wallpaper>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        listData = arrayListOf()
        val retrofitService = RetrofitService.getInstance()
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.wallList.observe(this, Observer {
            listData = it.data.wallpapers as ArrayList<Wallpaper>
            val adapter =  RecycleViewAdapter(viewModel, listData, this)
            binding.recyclerview.adapter = adapter
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getAllWall()
        val adapter =  RecycleViewAdapter(viewModel, listData, this)
        binding.recyclerview.adapter = adapter
        GridLayoutManager(this, 2, RecyclerView.VERTICAL, false).apply {
            binding.recyclerview.layoutManager = this
        }

        //
        adapter.click = { it
            Toast.makeText(this, "Closure : "+it.originUrlString(), Toast.LENGTH_SHORT).show()
        }

        //

        adapter.initOnClickInterface(this)
        setContentView(binding.root)
    }

    override fun onClick(data: Wallpaper) {
        Toast.makeText(this,"interface : "+data.originUrlString(), Toast.LENGTH_SHORT).show()
    }


}