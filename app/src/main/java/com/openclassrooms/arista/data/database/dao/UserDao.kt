package com.openclassrooms.arista.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.data.database.entities.UserDto
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Upsert
    suspend fun insertUser(user: UserDto): Long

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserDto>>

    @Delete
    suspend fun deleteUser(user: UserDto)
}