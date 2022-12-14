package com.example.fitnessapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercise")
    fun getExercises(): LiveData<List<Exercise>>
    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise")
    fun getExercisesList(): List<Exercise>

    @Query("Select * from exercise WHERE difficultyLevel=:difficulty")
    fun getDifficulty(difficulty: String?): LiveData<List<Exercise>>

    @Query("Select * from exercise WHERE difficultyLevel=:difficulty")
    fun getByDifficulty(difficulty: String?): Flow<List<Exercise>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllExercises(exercises: List<Exercise>?)

}