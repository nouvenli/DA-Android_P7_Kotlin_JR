package com.openclassrooms.arista.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.data.database.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow


/**
 * Data Access Object (DAO) for managing [ExerciseEntity] entity.
 *
 * This interface provides methods to perform Create, Read, Update, and Delete (CRUD) operations
 * on the "sleep" table. It uses Room annotations to define SQL queries and operations.
 *
 */
@Dao
interface ExerciseDao {
    @Upsert
    suspend fun insertExercise(exercise: ExerciseEntity): Long

    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<ExerciseEntity>>

    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)

}