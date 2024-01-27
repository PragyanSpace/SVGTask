package com.pragyan_space.simple_viral_games_assignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.pragyan_space.simple_viral_games_assignment.MyApplication
import com.pragyan_space.simple_viral_games_assignment.R
import com.pragyan_space.simple_viral_games_assignment.databinding.ActivityMainBinding
import com.pragyan_space.simple_viral_games_assignment.util.BitmapCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var cache:BitmapCache?=null
    lateinit var app:MyApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        app = this.application as MyApplication
        setupCache()
        binding.initListeners()
    }

    private fun setupCache() {
        cache=app.cache
        lifecycleScope.launch(Dispatchers.IO) {
            cache?.setupCache()
        }
    }

    private fun ActivityMainBinding.initListeners() {
        generate.setOnClickListener {
            startActivity(Intent(this@MainActivity,GenerateActivity::class.java))
        }
        showRecent.setOnClickListener {
            startActivity(Intent(this@MainActivity,RecentlyGeneratedActivity::class.java))
        }
    }
}