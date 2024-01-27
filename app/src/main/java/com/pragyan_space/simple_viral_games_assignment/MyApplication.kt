package com.pragyan_space.simple_viral_games_assignment

import android.app.Application
import androidx.lifecycle.lifecycleScope
import com.pragyan_space.simple_viral_games_assignment.util.BitmapCache
import com.pragyan_space.simple_viral_games_assignment.util.PrefUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MyApplication : Application() {
    var cache: BitmapCache? = null

    override fun onCreate() {
        super.onCreate()
        cache = BitmapCache(this,BitmapCache.cacheSize)
        buildCache()
    }

    private fun buildCache() {
        val listOfImages= PrefUtil(this).sharedPreferences?.getStringSet(PrefUtil.CACHE_DATA, setOf())?.toList()
        listOfImages?.reversed()?.forEach{
            cache?.queue?.enqueue(it)
        }
    }
}