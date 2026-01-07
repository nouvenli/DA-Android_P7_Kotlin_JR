package com.openclassrooms.arista.data.repository


import com.openclassrooms.arista.data.database.dao.UserDao
import com.openclassrooms.arista.data.mapper.toDomain
import com.openclassrooms.arista.data.mapper.toEntity
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.repository.UserRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository implementation for managing User data.
 *
 * This class acts as a mediator between the domain layer and the data layer (specifically the local database via [UserDao]).
 * It handles data operations such as retrieving, adding, and deleting a user, ensuring that domain models are
 * mapped to and from database entities.
 * * It implements the [UserRepositoryInterface] defined in the domain layer.
 *
 * @property userDao The Data Access Object used for interacting with the user table in the database.
 */
@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepositoryInterface {

    override fun getUser(): Flow<User?> {
        return userDao.getAllUsers().map { listEntity ->
            listEntity.firstOrNull()?.toDomain()
        }
    }

    override suspend fun addUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toEntity())
    }
}