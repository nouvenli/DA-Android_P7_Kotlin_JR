package com.openclassrooms.arista.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.data.database.entities.ExerciseDto
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDao {
    @Upsert
    suspend fun insertExercise(exercise: ExerciseDto): Long

    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<ExerciseDto>>

    @Delete
    suspend fun deleteExercise(exercise: ExerciseDto)

}