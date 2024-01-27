package com.pragyan_space.simple_viral_games_assignment.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import java.io.IOException
import java.io.Serializable
import java.net.URL


class BitmapCache(private val context: Context, maxSize: Int) : LruCache<String, Bitmap>(maxSize) {

    val queue= FixedQueue<String>()
    override fun sizeOf(key: String, value: Bitmap): Int {
        return value.byteCount / 1024
    }

    private fun getBitmap(key: String?): Bitmap? {
        return this.get(key)
    }

    fun getBitmapFromUrl(urlString:String):Bitmap?
    {
        try {
            val url = URL(urlString)
            return BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun getListOfBitMap():List<Bitmap>
    {
        val bitmaps= mutableListOf<Bitmap>()
        for (key in queue.toList())
        {
            getBitmap(key)?.let { bitmaps.add(it) }
        }
        return bitmaps.reversed()
    }

    fun clearCache()
    {
        this.evictAll()
    }

    fun setBitmap(key: String?, bitmap: Bitmap?) {
        if (!hasBitmap(key)) {
            this.put(key, bitmap)
            if (key != null) {
                PrefUtil(context).sharedPreferences?.edit()?.putStringSet(PrefUtil.CACHE_DATA,queue.toList().toSet())?.apply()
            }
        }
    }

    fun setupCache()
    {
        queue.toList().forEach {
            setBitmap(it,getBitmapFromUrl(it))
        }
    }

    private fun hasBitmap(key: String?): Boolean {
        return getBitmap(key) != null
    }

    companion object {
        val cacheSize: Int
            get() {
                val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
                return maxMemory / 8
            }
    }
}