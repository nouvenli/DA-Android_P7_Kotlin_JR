package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun execute(user: User) {
        userRepository.addUser(user)
    }
}