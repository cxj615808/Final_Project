package com.bignerdranch.android.finalproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.finalproject.Skill

@Database(entities = [Skill::class], version = 1)
@TypeConverters(FoodTypeConverters::class)
abstract class SkillDatabase  : RoomDatabase() {
    abstract fun skillDao(): SkillDao
}