package com.openclassrooms.arista.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.data.database.entities.UserDto
import kotlinx.coroutines.flow.Flow


/**
 * Data Access Object (DAO) interface for the [UserDto] entity.
 *
 * This interface defines the database methods to perform Create, Read, Update, and Delete (CRUD)
 * operations on the user table. It uses Room annotations to define SQL queries
 * and database interactions.
*/
@Dao
interface UserDao {
    @Upsert
    suspend fun insertUser(user: UserDto): Long

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserDto>>

    @Delete
    suspend fun deleteUser(user: UserDto)
}