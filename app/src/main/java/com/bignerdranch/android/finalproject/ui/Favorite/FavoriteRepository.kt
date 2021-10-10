package com.bignerdranch.android.finalproject.ui.Favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.finalproject.FavoriteFood
import com.bignerdranch.android.finalproject.database.FavoriteDatabase
import java.util.*
import java.util.concurrent.Executors


private const val DATABASE_NAME = "food-database"

class FavoriteRepository private constructor(context: Context){
    private val database : FavoriteDatabase = Room.databaseBuilder(
        context.applicationContext,
        FavoriteDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val favoriteDao = database.favoriteDao()
    private val executor = Executors.newSingleThreadExecutor()

    private val filesDir = context.applicationContext.filesDir

    fun getFavorites(): LiveData<List<FavoriteFood>> = favoriteDao.getFavorites()

    fun getFavorite(id: UUID): LiveData<FavoriteFood?> = favoriteDao.getFavorite(id)

    fun updateFavorite(favorite: FavoriteFood) {
        executor.execute {
            favoriteDao.updateFavorite(favorite)
        }
    }

    fun addFavorite(favorite: FavoriteFood) {
        executor.execute {
            favoriteDao.addFavorite(favorite)
        }
    }

    companion object {
        private var INSTANCE: FavoriteRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FavoriteRepository(context)
            }
        }

        fun get(): FavoriteRepository {
            return INSTANCE ?:
            throw IllegalStateException("FavoriteRepository must be initialized")
        }
    }
}