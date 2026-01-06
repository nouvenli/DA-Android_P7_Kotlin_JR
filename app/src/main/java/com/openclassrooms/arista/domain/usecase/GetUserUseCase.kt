package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.repository.UserRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUserUseCase @Inject constructor(private val userRepository: UserRepositoryInterface) {
    fun execute(): Flow<User?> {
        return userRepository.getUsers()
    }
}