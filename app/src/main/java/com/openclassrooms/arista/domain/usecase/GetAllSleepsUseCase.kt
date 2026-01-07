package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.repository.SleepRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Use case responsible for retrieving all sleep records.
 *
 * This class encapsulates the business logic for fetching a list of all available
 * [Sleep] entities from the repository. It follows the Clean Architecture pattern,
 * acting as an intermediary between the ViewModel and the Data layer.
 *
 * @property sleepRepository The repository interface used to access sleep data.
 * @constructor Creates an instance of [GetAllSleepsUseCase] with the necessary repository.
 */
class GetAllSleepsUseCase @Inject constructor(private val sleepRepository: SleepRepositoryInterface) {
    fun execute(): Flow<List<Sleep>> {
        return sleepRepository.getAllSleeps()
    }
}