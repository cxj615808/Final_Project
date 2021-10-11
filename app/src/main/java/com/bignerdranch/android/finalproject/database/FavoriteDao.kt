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
interface FavoriteDao {
    @Query("SELECT * FROM table_favorite")
    fun getFavorites(): LiveData<List<FavoriteFood>>

    @Query("SELECT * FROM table_favorite WHERE id=(:id)")
    fun getFavorite(id: UUID): LiveData<FavoriteFood?>

    @Update
    fun updateFavorite(favoriteFood: FavoriteFood)

    @Insert
    fun addFavorite(favoriteFood: FavoriteFood)
}