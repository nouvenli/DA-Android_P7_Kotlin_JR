package com.openclassrooms.arista.domain.repository

import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.Flow

interface SleepRepositoryInterface {
    fun getAllSleeps(): Flow<List<Sleep>>
    suspend fun addSleep(sleep: Sleep)
    suspend fun deleteSleep(sleep: Sleep)
}