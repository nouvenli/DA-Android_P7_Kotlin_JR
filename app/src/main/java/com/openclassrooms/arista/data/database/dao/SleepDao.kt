package com.openclassrooms.arista.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.data.database.entities.SleepDto
import kotlinx.coroutines.flow.Flow


/**
 * Data Access Object (DAO) for managing [SleepDto] entities in the database.
 *
 * This interface provides methods to perform Create, Read, Update, and Delete (CRUD)
 * operations on the "sleep" table. It uses Room annotations to define SQL queries
 * and database interactions.
 */
@Dao
interface SleepDao {
    @Upsert
    suspend fun insertSleep(sleep: SleepDto): Long

    @Query("SELECT * FROM sleep")
    fun getAllSleeps(): Flow<List<SleepDto>>

    @Delete
    suspend fun deleteSleep(sleep: SleepDto)

}