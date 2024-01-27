package com.pragyan_space.simple_viral_games_assignment.repository

import androidx.lifecycle.MutableLiveData
import com.pragyan_space.simple_viral_games_assignment.model.DogData
import com.pragyan_space.simple_viral_games_assignment.network.DogNetwork
import com.pragyan_space.simple_viral_games_assignment.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogRepository {

    val dogData=MutableLiveData<DogData>()

    fun getDog() {
        val client = RetrofitUtil.getRetrofit()?.create(DogNetwork::class.java)
        var call = client?.getDogImage()
        call?.enqueue(object : Callback<DogData?> {
            override fun onResponse(
                call: Call<DogData?>,
                response: Response<DogData?>
            ) {
                val body = response.body()
                if (response.isSuccessful) {
                    dogData.postValue(body)
                }
            }

            override fun onFailure(call: Call<DogData?>, t: Throwable) {

            }
        })
    }
}