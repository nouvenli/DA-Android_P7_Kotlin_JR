package com.openclassrooms.arista.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.openclassrooms.arista.data.database.dao.ExerciseDao
import com.openclassrooms.arista.data.database.dao.SleepDao
import com.openclassrooms.arista.data.database.dao.UserDao
import com.openclassrooms.arista.data.database.entities.ExerciseEntity
import com.openclassrooms.arista.data.database.entities.SleepEntity
import com.openclassrooms.arista.data.database.entities.UserEntity

/**
 * The Room database for the application.
 *
 * This abstract class serves as the main access point for the underlying SQLite connection.
 * It defines the entities included in the database and the Data Access Objects (DAOs)
 * used to interact with them.
 *
 * Entities:
 * - [UserEntity]: Represents user information.
 * - [ExerciseEntity]: Represents exercise records.
 * - [SleepEntity]: Represents sleep records.
 *
 * DAOs:
 * - [userDao]: Provides methods to interact with the user table.
 * - [exerciseDao]: Provides methods to interact with the exercise table.
 * - [sleepDao]: Provides methods to interact with the sleep table.
 *
 * @property userDao The DAO for user-related database operations.
 * @property exerciseDao The DAO for exercise-related database operations.
 * @property sleepDao The DAO for sleep-related database operations.
 */
@Database(
    entities = [UserEntity::class, ExerciseEntity::class, SleepEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun sleepDao(): SleepDao
    }