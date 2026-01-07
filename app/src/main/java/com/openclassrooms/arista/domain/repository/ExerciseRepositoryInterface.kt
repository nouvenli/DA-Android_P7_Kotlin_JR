package com.openclassrooms.arista.domain.repository

import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing [Exercise] data.
 *
 * This interface defines the contract for interacting with the data layer regarding exercises,
 * allowing the domain layer to remain agnostic of the specific data source implementation (e.g., Room database, API).
 */
interface ExerciseRepositoryInterface {
    fun getAllExercises(): Flow<List<Exercise>>
    suspend fun addExercise(exercise: Exercise)
    suspend fun deleteExercise(exercise: Exercise)

}