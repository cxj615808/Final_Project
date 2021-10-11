package com.bignerdranch.android.finalproject.ui.OwnRecipe

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.finalproject.FavoriteFood
import com.bignerdranch.android.finalproject.OwnFood
import com.bignerdranch.android.finalproject.Skill
import com.bignerdranch.android.finalproject.database.FavoriteDatabase
import com.bignerdranch.android.finalproject.database.OwnDatabase
import com.bignerdranch.android.finalproject.database.SkillDatabase
import java.util.*
import java.util.concurrent.Executors


private const val DATABASE_NAME = "skill-database"

class SkillRepository private constructor(context: Context){
    private val database : SkillDatabase = Room.databaseBuilder(
        context.applicationContext,
        SkillDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val skillDao = database.skillDao()
    private val executor = Executors.newSingleThreadExecutor()

    private val filesDir = context.applicationContext.filesDir

    fun getSkills(): LiveData<List<Skill>> = skillDao.getSkills()

    fun getSkill(id: UUID): LiveData<Skill?> = skillDao.getSkill(id)

    fun updateSkill(skill: Skill) {
        executor.execute {
            skillDao.updateSkill(skill)
        }
    }

    fun addSkill(skill: Skill) {
        executor.execute {
            skillDao.addSkill(skill)
        }
    }

    companion object {
        private var INSTANCE: SkillRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SkillRepository(context)
            }
        }

        fun get(): SkillRepository {
            return INSTANCE ?:
            throw IllegalStateException("FavoriteRepository must be initialized")
        }
    }
}