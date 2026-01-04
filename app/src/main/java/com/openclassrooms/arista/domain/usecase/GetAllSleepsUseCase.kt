package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.repository.SleepRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllSleepsUseCase @Inject constructor(private val sleepRepository: SleepRepositoryInterface) {
    fun execute(): Flow<List<Sleep>> {
        return sleepRepository.getAllSleeps()

    }
}