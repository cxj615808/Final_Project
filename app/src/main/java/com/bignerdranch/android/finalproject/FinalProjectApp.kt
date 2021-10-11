package com.bignerdranch.android.finalproject

import android.app.Application
import com.bignerdranch.android.finalproject.ui.Favorite.FavoriteRepository
import com.bignerdranch.android.finalproject.ui.OwnRecipe.OwnRecipeRepository

class FinalProjectApp : Application()  {

    override fun onCreate() {
        super.onCreate()
        FavoriteRepository.initialize(this)
        OwnRecipeRepository.initialize(this)
    }
}