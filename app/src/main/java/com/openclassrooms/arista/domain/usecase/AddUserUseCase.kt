package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.repository.UserRepositoryInterface
import javax.inject.Inject

/**
 * Use case dedicated to adding a new user to the application.
 *
 * This class encapsulates the business logic required to create a user entity.
 * It interacts with the [UserRepositoryInterface] to persist the user data.
 *
 * @property userRepository The repository interface used to access and modify user data.
 */
class AddUserUseCase @Inject constructor(private val userRepository: UserRepositoryInterface) {
    suspend fun execute(user: User) {
        userRepository.addUser(user)
    }
}