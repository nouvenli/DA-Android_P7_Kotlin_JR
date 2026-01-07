package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.database.dao.ExerciseDao
import com.openclassrooms.arista.data.mapper.toDomain
import com.openclassrooms.arista.data.mapper.toEntity
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.repository.ExerciseRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository implementation for managing Exercise data.
 *
 * This class acts as a single source of truth for exercise-related operations, bridging the gap
 * between the domain layer and the data layer (specifically the local database via [ExerciseDao]).
 * It handles data transformation between Domain models and Database entities.
 * * It implements the [ExerciseRepositoryInterface] defined in the domain layer.
 * @property exerciseDao The Data Access Object used for direct database interactions.
 */
@Singleton
class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ExerciseRepositoryInterface {

    override fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises().map { listEntity ->
            listEntity.map { it.toDomain() }
        }
    }

    override suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise.toEntity())
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise.toEntity())
    }
}