package com.example.tranningalo1.model.MainAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tranningalo1.R
import com.example.tranningalo1.databinding.ItemMainViewBinding
import com.example.tranningalo1.model.Wallpaper
import com.example.tranningalo1.viewmodel.MainViewModel

class RecycleViewAdapter(val viewModel: MainViewModel, val arrayList: ArrayList<Wallpaper>, val context: Context): RecyclerView.Adapter<RecycleViewAdapter.ImageViewHolder>() {
    var infaceClick : MainClickInterface? = null
    var click: ((data: Wallpaper)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_main_view,
            parent,
            false
        ) as ItemMainViewBinding
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if(arrayList.size==0){
            Toast.makeText(context,"List is empty",Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }
    fun initOnClickInterface(listener: MainClickInterface){
        this.infaceClick = listener
    }

    inner  class ImageViewHolder(private val binding: ItemMainViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Wallpaper){
            binding.text.text = data.name
            binding.text.setOnClickListener(){
                infaceClick?.onClick(data)
            }

            binding.root.setOnClickListener() {
                click?.invoke(data)
            }

            binding.deleted.setOnClickListener {
                //
                Toast.makeText(context,"Context: " + data.thumbUrlString(),Toast.LENGTH_SHORT).show()
            }
            Glide.with(context).load(data.originUrlString()).into(binding.imageView)
        }

    }
    inner  class VidioViewHolder(private val binding: ItemMainViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Wallpaper){
            binding.text.text = data.name
            binding.text.setOnClickListener(){
                infaceClick?.onClick(data)
            }

            binding.root.setOnClickListener() {
                click?.invoke(data)
            }

            binding.deleted.setOnClickListener {
                //
                Toast.makeText(context,"Context: " + data.thumbUrlString(),Toast.LENGTH_SHORT).show()
            }
            Glide.with(context).load(data.originUrlString()).into(binding.imageView)
        }

    }
}