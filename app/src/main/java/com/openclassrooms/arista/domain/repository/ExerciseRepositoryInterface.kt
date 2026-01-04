package com.openclassrooms.arista.domain.repository

import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepositoryInterface {
    fun getAllExercises(): Flow<List<Exercise>>
    suspend fun addExercise(exercise: Exercise)
    suspend fun deleteExercise(exercise: Exercise)

}