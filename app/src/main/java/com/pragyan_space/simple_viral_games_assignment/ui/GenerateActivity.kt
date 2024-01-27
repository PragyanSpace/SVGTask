package com.pragyan_space.simple_viral_games_assignment.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.pragyan_space.simple_viral_games_assignment.MyApplication
import com.pragyan_space.simple_viral_games_assignment.R
import com.pragyan_space.simple_viral_games_assignment.databinding.ActivityGenerateBinding
import com.pragyan_space.simple_viral_games_assignment.util.BitmapCache
import com.pragyan_space.simple_viral_games_assignment.viewmodel.DogViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenerateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenerateBinding
    private lateinit var viewmodel:DogViewModel
    private lateinit var cache:BitmapCache
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_generate)
        viewmodel= ViewModelProvider(this)[DogViewModel::class.java]
        initObservers()
        val app = this.application as MyApplication
        cache= app.cache!!
        binding.initListeners()
    }

    private fun initObservers() {
        viewmodel.dogData.observe(this){
            Glide.with(this).load(it.imageUrl).into(binding.imageView)
            lifecycleScope.launch(Dispatchers.IO) {
                it.imageUrl?.let { it1 -> cache.queue.enqueue(it1) }
                cache.setBitmap(it.imageUrl,cache.getBitmapFromUrl(it.imageUrl?:""))
            }
        }
    }

    private fun ActivityGenerateBinding.initListeners() {
        generate.setOnClickListener {
            viewmodel.getDog()
        }
    }
}
