package com.bignerdranch.android.finalproject

import android.app.Application
import com.bignerdranch.android.finalproject.ui.Favorite.FavoriteRepository
import com.bignerdranch.android.finalproject.ui.OwnRecipe.OwnRecipeRepository
import com.bignerdranch.android.finalproject.ui.OwnRecipe.SkillRepository

class FinalProjectApp : Application()  {

    override fun onCreate() {
        super.onCreate()
        FavoriteRepository.initialize(this)
        OwnRecipeRepository.initialize(this)
        SkillRepository.initialize(this)

    }
}