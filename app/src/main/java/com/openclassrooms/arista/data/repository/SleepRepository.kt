package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.database.dao.SleepDao
import com.openclassrooms.arista.data.mapper.toDomain
import com.openclassrooms.arista.data.mapper.toEntity
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.repository.SleepRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository implementation for managing Sleep data.
 *
 * This class acts as a single source of truth for sleep-related data, mediating between the
 * domain layer and the data layer (database). It handles the conversion between
 * database entities (DTOs) and domain models using mappers.
 *
 * It implements the [SleepRepositoryInterface] defined in the domain layer.
 *
 * @property SleepDao The Data Access Object used for database operations on sleep records.
 */
@Singleton
class SleepRepository @Inject constructor(
    private val SleepDao: SleepDao
) : SleepRepositoryInterface {

    override fun getAllSleeps(): Flow<List<Sleep>> {
        return SleepDao.getAllSleeps().map { listEntity ->
            listEntity.map { it.toDomain() } // Utilisation du mapper
        }
    }

    override suspend fun addSleep(sleep: Sleep) {
        SleepDao.insertSleep(sleep.toEntity())
    }

    override suspend fun deleteSleep(sleep: Sleep) {
        SleepDao.deleteSleep(sleep.toEntity())
    }
}