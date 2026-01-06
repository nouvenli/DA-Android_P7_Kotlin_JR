package com.openclassrooms.arista.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.data.database.entities.ExerciseDto
import kotlinx.coroutines.flow.Flow


/**
 * Data Access Object (DAO) for managing [ExerciseDto] entity.
 *
 * This interface provides methods to perform Create, Read, Update, and Delete (CRUD) operations
 * on the "sleep" table. It uses Room annotations to define SQL queries and operations.
 *
 */
@Dao
interface ExerciseDao {
    @Upsert
    suspend fun insertExercise(exercise: ExerciseDto): Long

    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<ExerciseDto>>

    @Delete
    suspend fun deleteExercise(exercise: ExerciseDto)

}