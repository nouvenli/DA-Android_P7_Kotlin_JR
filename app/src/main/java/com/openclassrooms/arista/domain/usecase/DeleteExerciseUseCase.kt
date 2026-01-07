package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.repository.ExerciseRepositoryInterface
import javax.inject.Inject

/**
 * Use case responsible for deleting an existing exercise.
 *
 * This class encapsulates the business logic required to remove an exercise from the data source
 * by delegating the operation to the [ExerciseRepositoryInterface]. It is designed to be called
 * from a ViewModel or another domain layer component.
 *
 * @property exerciseRepository The repository interface used to perform the deletion operation.
 */
class DeleteExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepositoryInterface) {
    suspend fun execute(exercise: Exercise) {
        exerciseRepository.deleteExercise(exercise)
    }
}