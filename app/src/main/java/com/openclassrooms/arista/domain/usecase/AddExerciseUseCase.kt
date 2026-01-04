package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.repository.ExerciseRepositoryInterface
import javax.inject.Inject

class AddExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepositoryInterface) {
    suspend fun execute(exercise: Exercise) {
        exerciseRepository.addExercise(exercise)
    }
}