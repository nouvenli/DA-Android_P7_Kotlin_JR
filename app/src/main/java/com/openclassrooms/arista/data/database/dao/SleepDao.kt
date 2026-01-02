package com.openclassrooms.arista.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.data.database.entities.SleepDto
import kotlinx.coroutines.flow.Flow


@Dao
interface SleepDao {
    @Upsert
    suspend fun insertSleep(sleep: SleepDto): Long

    @Query("SELECT * FROM sleep")
    fun getAllSleeps(): Flow<List<SleepDto>>

    @Delete
    suspend fun deleteSleep(sleep: SleepDto)

}