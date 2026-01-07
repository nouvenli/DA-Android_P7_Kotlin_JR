package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.repository.ExerciseRepositoryInterface
import javax.inject.Inject

/**
 * Use case responsible for adding a new exercise to the application data.
 *
 * This class encapsulates the business logic for creating a new exercise entry.
 * It interacts with the [ExerciseRepositoryInterface] to persist the data.
 *
 * @property exerciseRepository The repository interface used to access and modify exercise data.
 */
class AddExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepositoryInterface) {
    suspend fun execute(exercise: Exercise) {
        exerciseRepository.addExercise(exercise)
    }
}