package com.bignerdranch.android.finalproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.finalproject.FavoriteFood
import com.bignerdranch.android.finalproject.OwnFood
import java.util.*

@Dao
interface FoodDao {
    @Query("SELECT * FROM table_favorite")
    fun getFavorites(): LiveData<List<FavoriteFood>>

    @Query("SELECT * FROM table_favorite WHERE id=(:id)")
    fun getFavorite(id: UUID): LiveData<FavoriteFood?>

    @Query("SELECT * FROM table_own")
    fun getOwns(): LiveData<List<OwnFood>>

    @Query("SELECT * FROM table_own WHERE id=(:id)")
    fun getOwn(id: UUID): LiveData<OwnFood?>

    @Update
    fun updateFavorite(favoriteFood: FavoriteFood)

    @Update
    fun updateOwn(ownFood: OwnFood)

    @Insert
    fun addFavorite(favoriteFood: FavoriteFood)

    @Insert
    fun addOwn(ownFood: OwnFood)
}