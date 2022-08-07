package com.example.moviewatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviewatcher.ViewModel.MainActivityViewModel
import com.example.moviewatcher.databinding.ActivityMainBinding
import com.example.moviewatcher.model.Video
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        initViewModel(binding)
        initMainViewModel()
    }

    private fun initViewModel(binding: ActivityMainBinding) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            val decoration  =  DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun initMainViewModel() {
        val viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllVideoList().observe(this, Observer<List<Video>>{

            for(res in it){
                Log.d("this_out",res.title.toString())
            }
        })
        viewModel.makeApiRequest()

    }


}