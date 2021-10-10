package com.bignerdranch.android.finalproject.ui.Favorite

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.finalproject.FavoriteFood

class FavoriteViewModel  : ViewModel() {

    private val favoriteRepository = FavoriteRepository.get()
    val favoriteLiveData = favoriteRepository.getFavorites()

    fun addFood(favoriteFood: FavoriteFood) {
        favoriteRepository.addFavorite(favoriteFood)
    }
}