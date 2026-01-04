package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.database.dao.SleepDao
import com.openclassrooms.arista.data.mapper.toDomain
import com.openclassrooms.arista.data.mapper.toDto
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.repository.SleepRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SleepRepository @Inject constructor(
    private val SleepDao: SleepDao
) : SleepRepositoryInterface {
    // recupérer les données de sleep (dto en exercice du domaine)
    override fun getAllSleeps(): Flow<List<Sleep>> {
        return SleepDao.getAllSleeps().map { listDto ->
            listDto.map { it.toDomain() } // Utilisation du mapper
        }
    }
    // ajouter un nouveau sleep (transforme le Sleep du domaine en DTO)
    override suspend fun addSleep(sleep: Sleep) {
        SleepDao.insertSleep(sleep.toDto())
    }
    // supprimer un sleep
    override suspend fun deleteSleep(sleep: Sleep) {
        SleepDao.deleteSleep(sleep.toDto())
    }
 }