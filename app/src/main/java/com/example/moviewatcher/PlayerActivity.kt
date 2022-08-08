package com.example.moviewatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviewatcher.databinding.ActivityMainBinding
import com.example.moviewatcher.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityPlayerBinding>(
            this, R.layout.activity_player
        )
        setContentView(binding.root)

        var videoData = intent
        var title = videoData.getStringExtra("title")
        var link = videoData.getStringExtra("link")
        binding.title.text = title.toString()

    }
}