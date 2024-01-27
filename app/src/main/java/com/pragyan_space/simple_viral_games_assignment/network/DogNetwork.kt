package com.pragyan_space.simple_viral_games_assignment.network

import com.pragyan_space.simple_viral_games_assignment.model.DogData
import com.pragyan_space.simple_viral_games_assignment.util.AppUrls
import retrofit2.Call
import retrofit2.http.GET

interface DogNetwork {
    @GET(AppUrls.getDog)
    fun getDogImage(): Call<DogData>
}