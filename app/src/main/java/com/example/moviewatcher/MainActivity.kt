package com.example.moviewatcher

import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

    var prefs: SharedPreferences? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    var videoList: List<Video> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        setContentView(binding.root)
        prefs = getSharedPreferences("com.example.moviewatcher", MODE_PRIVATE);
        manager = LinearLayoutManager(this)
        if (haveNetworkConnection()) {
            initMainViewModel()
        } else {
            Toast.makeText(this, "Please find internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun haveNetworkConnection(): Boolean {
        var haveConnectedWifi = false
        var haveConnectedMobile = false
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.allNetworkInfo
        for (networkItem in netInfo) {
            if (networkItem.typeName.equals(
                    "WIFI",
                    ignoreCase = true
                )
            ) if (networkItem.isConnected) haveConnectedWifi = true
            if (networkItem.typeName.equals(
                    "MOBILE",
                    ignoreCase = true
                )
            ) if (networkItem.isConnected) haveConnectedMobile = true
        }
        return haveConnectedWifi || haveConnectedMobile
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
        if (prefs?.getBoolean("firstrun", true)!!) {
            viewModel.makeApiRequest()
            prefs!!.edit().putBoolean("firstrun", false).commit();
        }

    }

}