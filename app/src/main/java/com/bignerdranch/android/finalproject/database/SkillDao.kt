package com.bignerdranch.android.finalproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.finalproject.FavoriteFood
import com.bignerdranch.android.finalproject.Skill
import java.util.*

@Dao
interface SkillDao {
    @Query("SELECT * FROM table_skill")
    fun getSkills(): LiveData<List<Skill>>

    @Query("SELECT * FROM table_skill WHERE id=(:id)")
    fun getSkill(id: UUID): LiveData<Skill?>

    @Update
    fun updateSkill(skill: Skill)

    @Insert
    fun addSkill(skill: Skill)
}