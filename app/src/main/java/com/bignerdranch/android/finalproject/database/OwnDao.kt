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
interface OwnDao {
    @Query("SELECT * FROM table_own")
    fun getOwns(): LiveData<List<OwnFood>>

    @Query("SELECT * FROM table_own WHERE id=(:id)")
    fun getOwn(id: UUID): LiveData<OwnFood?>

    @Update
    fun updateOwn(ownFood: OwnFood)

    @Insert
    fun addOwn(ownFood: OwnFood)
}