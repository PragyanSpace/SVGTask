package com.pragyan_space.simple_viral_games_assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pragyan_space.simple_viral_games_assignment.model.DogData
import com.pragyan_space.simple_viral_games_assignment.repository.DogRepository

class DogViewModel: ViewModel() {
    private val dogRepo=DogRepository()
    val dogData: LiveData<DogData>

    init {
        dogData=dogRepo.dogData
    }

    fun getDog()
    {
        dogRepo.getDog()
    }

}