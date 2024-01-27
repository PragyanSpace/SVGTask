package com.pragyan_space.simple_viral_games_assignment.util

import android.content.Context
import android.content.SharedPreferences
import java.lang.ref.WeakReference

class PrefUtil(context: Context) {
    var sharedPreferences: SharedPreferences? = null
    private val context: WeakReference<Context>

    init {
        this.context = WeakReference<Context>(context)
        sharedPreferences = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE)
    }

    companion object {
        const val CACHE_DATA = "CACHE_DATA"
        const val SESSION = "SESSION"
    }

    fun removeSavedValue(){
        val edit= sharedPreferences?.edit()
        edit?.remove(CACHE_DATA)
        edit?.apply()
    }
}