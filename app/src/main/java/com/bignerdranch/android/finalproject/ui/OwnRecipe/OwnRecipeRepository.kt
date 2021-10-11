package com.bignerdranch.android.finalproject.ui.OwnRecipe

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.finalproject.FavoriteFood
import com.bignerdranch.android.finalproject.OwnFood
import com.bignerdranch.android.finalproject.database.FavoriteDatabase
import com.bignerdranch.android.finalproject.database.OwnDatabase
import java.util.*
import java.util.concurrent.Executors


private const val DATABASE_NAME = "ownrecipe-database"

class OwnRecipeRepository private constructor(context: Context){
    private val database : OwnDatabase = Room.databaseBuilder(
        context.applicationContext,
        OwnDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val ownrecipeDao = database.OwnDao()
    private val executor = Executors.newSingleThreadExecutor()

    private val filesDir = context.applicationContext.filesDir

    fun getOwnRecipes(): LiveData<List<OwnFood>> = ownrecipeDao.getOwns()

    fun getOwnRecipe(id: UUID): LiveData<OwnFood?> = ownrecipeDao.getOwn(id)

    fun updateOwn(Own: OwnFood) {
        executor.execute {
            ownrecipeDao.updateOwn(Own)
        }
    }

    fun addOwn(Own: OwnFood) {
        executor.execute {
            ownrecipeDao.addOwn(Own)
        }
    }

    companion object {
        private var INSTANCE: OwnRecipeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = OwnRecipeRepository(context)
            }
        }

        fun get(): OwnRecipeRepository {
            return INSTANCE ?:
            throw IllegalStateException("FavoriteRepository must be initialized")
        }
    }
}