package com.pragyan_space.simple_viral_games_assignment.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pragyan_space.simple_viral_games_assignment.MyApplication
import com.pragyan_space.simple_viral_games_assignment.R
import com.pragyan_space.simple_viral_games_assignment.databinding.ActivityRecentlyGeneratedBinding
import com.pragyan_space.simple_viral_games_assignment.ui.adapter.DogsRVAdapter
import com.pragyan_space.simple_viral_games_assignment.util.PrefUtil


class RecentlyGeneratedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentlyGeneratedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_recently_generated)
        val app = this.application as MyApplication
        val cache=app.cache
        Log.d("asdasdasd",cache?.queue?.size().toString())
        val dogAdapter=cache?.getListOfBitMap()
            ?.let { DogsRVAdapter(this@RecentlyGeneratedActivity, it) }
        binding.rv.apply {
            layoutManager=LinearLayoutManager(this@RecentlyGeneratedActivity,LinearLayoutManager.HORIZONTAL,false)
            adapter= dogAdapter
        }
        binding.clearBtn.setOnClickListener {
            cache?.clearCache()
            dogAdapter?.clearData()
            PrefUtil(this).removeSavedValue()
        }
    }
}