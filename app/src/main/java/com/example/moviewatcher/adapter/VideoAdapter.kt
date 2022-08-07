package com.example.moviewatcher.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviewatcher.databinding.VideoInfoItemBinding
import com.example.moviewatcher.model.Video
import com.squareup.picasso.Picasso

class VideoAdapter(private val videoList :List<Video>, context: Context) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var context: Context = context

    inner class VideoViewHolder(val binding: VideoInfoItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        var imageView : ImageView = binding.videoPicture
        fun bind(item: Video){
            binding.video= item
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoAdapter.VideoViewHolder {


       val inflater = LayoutInflater.from(parent.context)
        val listVideoInfoItemBinding = VideoInfoItemBinding.inflate(
            inflater, parent, false)
        return VideoViewHolder(listVideoInfoItemBinding)

    }

    override fun onBindViewHolder(holder: VideoAdapter.VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
        Picasso.with(context).load(videoList[position].thumb)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return videoList.size
    }


}