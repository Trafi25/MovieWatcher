package com.example.moviewatcher

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviewatcher.Utils.Common
import com.example.moviewatcher.ViewModel.MainActivityViewModel
import com.example.moviewatcher.adapter.VideoAdapter
import com.example.moviewatcher.databinding.ActivityMainBinding
import com.example.moviewatcher.model.Video
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private  lateinit var recyclerView: RecyclerView
    private lateinit var adapter :VideoAdapter
    var videoList : List<Video> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        setContentView(binding.root)
        manager = LinearLayoutManager(this)

        initMainViewModel()


    }

    private fun initMainViewModel() {


        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllVideoList().observe(this, {
            videoList = it
            Common.setVideos(videoList)
            binding.recyclerView.apply {
                adapter = VideoAdapter(videoList, this@MainActivity)
                layoutManager = manager
            }
            //Log.d("tutu", Common.videoList.size.toString())
        })
        viewModel.makeApiRequest()
    }

}