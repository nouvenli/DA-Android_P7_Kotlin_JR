package com.openclassrooms.arista.domain.repository

import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing sleep data.
 *
 * This interface defines the contract for interacting with sleep-related data sources,
 * such as a local database or a remote API. It provides methods to observe sleep history,
 * add new sleep records, and remove existing ones.
 */
interface SleepRepositoryInterface {
    fun getAllSleeps(): Flow<List<Sleep>>
    suspend fun addSleep(sleep: Sleep)
    suspend fun deleteSleep(sleep: Sleep)
}