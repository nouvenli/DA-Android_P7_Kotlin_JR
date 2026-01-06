package com.openclassrooms.arista.domain.repository

import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {
    fun getUsers(): Flow<User?>
    suspend fun addUser(user: User)
    suspend fun deleteUser(user: User)
}