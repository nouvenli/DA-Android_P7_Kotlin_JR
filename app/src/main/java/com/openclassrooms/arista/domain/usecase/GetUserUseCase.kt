package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.repository.UserRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Use case responsible for retrieving the currently logged-in or active user.
 *
 * This class encapsulates the business logic for fetching user details from the
 * [UserRepositoryInterface]. It returns a reactive flow of the user state,
 * allowing the UI to react to changes in user data.
 *
 * @property userRepository The repository used to access user data.
 */
class GetUserUseCase @Inject constructor(private val userRepository: UserRepositoryInterface) {
    fun execute(): Flow<User?> {
        return userRepository.getUser()
    }
}