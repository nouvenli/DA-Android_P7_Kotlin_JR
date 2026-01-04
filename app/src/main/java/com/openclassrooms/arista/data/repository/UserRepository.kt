package com.openclassrooms.arista.data.repository

//import androidx.paging.map
//import com.openclassrooms.arista.data.FakeApiService
import com.openclassrooms.arista.data.database.dao.UserDao
import com.openclassrooms.arista.data.database.entities.UserDto
import com.openclassrooms.arista.data.mapper.toDomain
import com.openclassrooms.arista.data.mapper.toDto
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.repository.UserRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/*
*utilisation de hilt pour l'injection de dépendances
 */

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepositoryInterface {

    // Récupérer tous les utilisateurs (transforme les DTOs en Users du domaine)
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { listDto ->
            listDto.map { it.toDomain() } // Utilisation du mapper
        }
    }

    // Insérer un utilisateur (transforme le User du domaine en DTO)
    override suspend fun addUser(user: User) {
        userDao.insertUser(user.toDto())
    }

    // Supprimer un utilisateur
    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toDto())
    }
}