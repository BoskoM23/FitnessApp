package com.example.fitnessapp.repository

import androidx.lifecycle.LiveData
import com.example.fitnessapp.db.Exercise
import com.example.fitnessapp.db.ExerciseDao
import kotlinx.coroutines.flow.Flow


class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    fun getAllExercises(): LiveData<List<Exercise>> {
        return exerciseDao.getExercises()
    }
    fun getExercisesByDifficulty(difficulty: String): LiveData<List<Exercise>>{
        return exerciseDao.getDifficulty(difficulty)
    }

    fun getAllOrSearch(difficulty: String): Flow<List<Exercise>> {
        return if (difficulty == "all") {
            exerciseDao.getAllExercises()
        } else {
            exerciseDao.getByDifficulty(difficulty)
        }
    }
}