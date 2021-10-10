package com.bignerdranch.android.finalproject.ui.Favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.finalproject.FavoriteFood
import java.util.*

class FavoriteViewModel  : ViewModel() {

    private val favoriteRepository = FavoriteRepository.get()
    val favoriteLiveData = favoriteRepository.getFavorites()
    private val favoriteIdLiveData = MutableLiveData<UUID>()

    fun addfavorite(favoriteFood: FavoriteFood) {
        favoriteRepository.addFavorite(favoriteFood)
    }

    fun loadFavorite(favoriteId: UUID) {
        favoriteIdLiveData.value = favoriteId
    }
}