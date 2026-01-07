package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.repository.ExerciseRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Use case responsible for retrieving all available exercises.
 *
 * This class encapsulates the business logic for fetching the full list of exercises
 * from the data layer via the [ExerciseRepositoryInterface]. It exposes the data
 * as a reactive stream using [Flow].
 *
 * @property exerciseRepository The repository interface used to access exercise data.
 */
class GetAllExercisesUseCase @Inject constructor(private val exerciseRepository: ExerciseRepositoryInterface) {
    fun execute(): Flow<List<Exercise>> {
        return exerciseRepository.getAllExercises()
    }
}