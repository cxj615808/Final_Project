package com.bignerdranch.android.finalproject.ui.Skill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.finalproject.OwnFood
import com.bignerdranch.android.finalproject.Skill
import com.bignerdranch.android.finalproject.ui.OwnRecipe.OwnRecipeRepository
import com.bignerdranch.android.finalproject.ui.OwnRecipe.SkillRepository
import java.util.*

class SkillViewModel : ViewModel() {

    private val skillRepository = SkillRepository.get()
    val skillLiveData = skillRepository.getSkills()
    private val skillIdLiveData = MutableLiveData<UUID>()

    fun addskill(skill: Skill) {
        skillRepository.addSkill(skill)
    }

    fun loadskill(skillId: UUID) {
        skillIdLiveData.value = skillId
    }
}