package com.example.moviewatcher.adapter


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviewatcher.Player.PlayerActivity
import com.example.moviewatcher.R
import com.example.moviewatcher.databinding.VideoInfoItemBinding
import com.example.moviewatcher.model.Video

class VideoAdapter(private val videoList: List<Video>, private val context: Context) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(val videoItemBinding: VideoInfoItemBinding) :
        RecyclerView.ViewHolder(videoItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val infalter = LayoutInflater.from(parent.context)
        val videoItemBinding = DataBindingUtil.inflate<VideoInfoItemBinding>(
            infalter,
            R.layout.video_info_item, parent, false
        )


        return VideoViewHolder(videoItemBinding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videosLists = videoList[position]
        holder.videoItemBinding.video = videosLists
        try {
            Glide
                .with(context)
                .load(videosLists.thumb)
                .centerCrop()
                .placeholder(R.drawable.loaing)
                .into(holder.videoItemBinding.videoPicture)
        } catch (e: Exception) {
            Log.d("except", e.toString())
        }
        holder.videoItemBinding.root.setOnClickListener {
            var cfwef = videosLists.title
            var videIntent = Intent(context, PlayerActivity::class.java)
            videIntent.putExtra("video", videosLists.title)
            videIntent.putExtra("link", videosLists.sources?.get(0))
            context.startActivity(videIntent)
        }

    }

    override fun getItemCount(): Int {
        return videoList.size
    }


}