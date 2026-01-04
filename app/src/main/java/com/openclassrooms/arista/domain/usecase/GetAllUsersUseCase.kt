package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.repository.UserRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllUsersUseCase @Inject constructor(private val userRepository: UserRepositoryInterface) {
    fun execute(): Flow<List<User>> {
        return userRepository.getAllUsers()
    }
}