package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.database.dao.ExerciseDao
import com.openclassrooms.arista.data.mapper.toDomain
import com.openclassrooms.arista.data.mapper.toDto
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.repository.ExerciseRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// getall, addnew, delete

@Singleton
class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ExerciseRepositoryInterface {
    // recupérer les données de exercise (dto en exercice du domaine)
    override fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises().map { listDto ->
            listDto.map { it.toDomain() } // Utilisation du mapper
        }
    }

    // ajouter un nouvel exercise (transforme le Exercise du domaine en DTO)
    override suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise.toDto())
    }

    // supprimer un exercise
    override suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise.toDto())
    }
}