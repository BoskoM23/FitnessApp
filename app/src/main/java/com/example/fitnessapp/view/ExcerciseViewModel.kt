package com.example.fitnessapp.view

import android.app.Application
import androidx.lifecycle.*
import com.example.fitnessapp.db.Exercise
import com.example.fitnessapp.db.ExerciseDatabase
import com.example.fitnessapp.repository.ExerciseRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class ExercisesViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository: ExerciseRepository
    val difficulty = MutableStateFlow("all")

    init {
        val exerciseDao = ExerciseDatabase
            .getDatabase(application, viewModelScope, application.resources)
            .exerciseDao()
        repository = ExerciseRepository(exerciseDao)
    }

    val selectedExercise = MutableLiveData<Exercise>()

    fun setSelectedExercise(exercise: Exercise){
        selectedExercise.value = exercise
    }

    fun getAllExercises(): LiveData<List<Exercise>>{
        return repository.getAllExercises()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val exercises = difficulty.flatMapLatest {
        repository.getAllOrSearch(it)
    }.asLiveData()

    fun getExercisesByDifficulty(difficulty: String): LiveData<List<Exercise>>{
        return repository.getExercisesByDifficulty(difficulty)
    }


}