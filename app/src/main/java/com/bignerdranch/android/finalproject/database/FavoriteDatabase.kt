package com.bignerdranch.android.finalproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.finalproject.FavoriteFood

@Database(entities = [FavoriteFood::class], version = 1)
@TypeConverters(FoodTypeConverters::class)
abstract class FavoriteDatabase  : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}