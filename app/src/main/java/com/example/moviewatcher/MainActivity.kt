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
            Log.d("tutu", it.size.toString())
            val videoList : List<Video> = it
            binding.recyclerView.apply {
                adapter = VideoAdapter(videoList, context)
                layoutManager = manager
            }
        })
        viewModel.makeApiRequest()
    }

/*    private fun fillRecyclerView() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllVideoList().observe(this, {
            Log.d("tutu", it.size.toString())
            val videoList : List<Video> = it
        recyclerView = binding.recyclerView
        adapter = VideoAdapter(videoList,this)
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_PORTRAIT
        ) {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        })
    }*/


}