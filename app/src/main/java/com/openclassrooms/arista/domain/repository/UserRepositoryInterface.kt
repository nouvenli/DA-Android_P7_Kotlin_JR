package com.openclassrooms.arista.domain.repository

import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing [User] data.
 *
 * This interface defines the contract for accessing and modifying user information,
 * bridging the domain layer with the data layer. It provides methods to retrieve the current
 * user as a stream, as well as to add or delete a user asynchronously.
 */
interface UserRepositoryInterface {
    fun getUser(): Flow<User?>
    suspend fun addUser(user: User)
    suspend fun deleteUser(user: User)
}