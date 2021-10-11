package com.bignerdranch.android.finalproject.ui.OwnRecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.finalproject.OwnFood
import java.util.*

class OwnRecipeViewModel : ViewModel()  {
    private val ownRepository = OwnRecipeRepository.get()
    val ownLiveData = ownRepository.getOwnRecipes()
    private val ownIdLiveData = MutableLiveData<UUID>()

    fun addown(ownFood: OwnFood) {
        ownRepository.addOwn(ownFood)
    }

    fun loadOwn(favoriteId: UUID) {
        ownIdLiveData.value = favoriteId
    }
}