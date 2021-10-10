package com.bignerdranch.android.finalproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.finalproject.OwnFood

@Database(entities = [OwnFood::class], version = 1)
@TypeConverters(FoodTypeConverters::class)
abstract class OwnDatabase  : RoomDatabase() {
    abstract fun OwnDao(): FoodDao
}