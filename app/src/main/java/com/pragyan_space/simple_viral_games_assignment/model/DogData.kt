package com.pragyan_space.simple_viral_games_assignment.model

import com.google.gson.annotations.SerializedName


data class DogData (

    @SerializedName("message" ) var imageUrl : String? = null,
    @SerializedName("status"  ) var status  : String? = null

)
