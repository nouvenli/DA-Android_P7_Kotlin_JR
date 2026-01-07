package com.openclassrooms.arista.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassrooms.arista.data.database.AppDatabase
import com.openclassrooms.arista.data.database.dao.ExerciseDao
import com.openclassrooms.arista.data.database.dao.SleepDao
import com.openclassrooms.arista.data.database.dao.UserDao
import com.openclassrooms.arista.data.database.entities.ExerciseEntity
import com.openclassrooms.arista.data.database.entities.UserEntity
import com.openclassrooms.arista.data.database.entities.SleepEntity
import com.openclassrooms.arista.domain.model.ExerciseCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Provider
import javax.inject.Singleton


/**
 * Hilt module responsible for providing database-related dependencies.
 *
 * This module manages the creation and lifecycle of the Room database instance (`AppDatabase`)
 * as well as the Data Access Objects (DAOs) for Users, Exercises, and Sleep sessions.
 * It ensures that a single instance of the database is used throughout the application (Singleton)
 * and pre-populates the database with initial dummy data upon creation.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context, provider: Provider<AppDatabase>
    ): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "app_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    val database = provider.get()
                    val userDao = database.userDao()
                    val exerciseDao = database.exerciseDao()
                    val sleepDao = database.sleepDao()

                    populateDatabase(userDao, exerciseDao, sleepDao)
                }
            }

        }).build()
    }

    // --- Populates the database with initial dummy data ---

    /**
     *
     * This function is suspended and should be called from a coroutine scope (e.g., [Dispatchers.IO]).
     * It inserts a default user, several exercise sessions (Running, Swimming, Football), and
     * several sleep sessions with varying durations and qualities into their respective tables.
     * This is typically triggered during the `onCreate` callback of the Room database.
     *
     * @param userDao The Data Access Object for User operations.
     * @param exerciseDao The Data Access Object for Exercise operations.
     * @param sleepDao The Data Access Object for Sleep operations.
     */
    suspend fun populateDatabase(userDao: UserDao, exerciseDao: ExerciseDao, sleepDao: SleepDao) {
        userDao.insertUser(
            UserEntity(
                name = "AnneOnyme", email = "AnneOnyme@example.com"
            )
        )

        exerciseDao.insertExercise(
            ExerciseEntity(
                startTime = LocalDateTime.now().minusHours(5).atZone(ZoneOffset.UTC).toInstant()
                    .toEpochMilli(),
                duration = 30,
                category = ExerciseCategory.Running,
                intensity = 7
            )
        )
        exerciseDao.insertExercise(
            ExerciseEntity(
                startTime = LocalDateTime.now().minusDays(1).minusHours(3).atZone(ZoneOffset.UTC)
                    .toInstant().toEpochMilli(),
                duration = 45,
                category = ExerciseCategory.Swimming,
                intensity = 6
            )
        )
        exerciseDao.insertExercise(
            ExerciseEntity(
                startTime = LocalDateTime.now().minusDays(2).minusHours(4).atZone(ZoneOffset.UTC)
                    .toInstant().toEpochMilli(),
                duration = 60,
                category = ExerciseCategory.Football,
                intensity = 8
            )
        )

        sleepDao.insertSleep(
            SleepEntity(
                startTime = LocalDateTime.now().minusDays(1).atZone(ZoneOffset.UTC).toInstant()
                    .toEpochMilli(), duration = 420, quality = 8
            )
        )
        sleepDao.insertSleep(
            SleepEntity(
                startTime = LocalDateTime.now().minusDays(2).atZone(ZoneOffset.UTC).toInstant()
                    .toEpochMilli(), duration = 360, quality = 5
            )
        )
        sleepDao.insertSleep(
            SleepEntity(
                startTime = LocalDateTime.now().minusDays(3).atZone(ZoneOffset.UTC).toInstant()
                    .toEpochMilli(), duration = 480, quality = 9
            )
        )
    }

//   --- Provides the DAOs ---

    /**
     * Provides the [UserDao] [ExerciseDao] and [SleepDao] instance.
     *
     * This function retrieves the Data Access Object (DAO) for accessing User data
     * from the provided [AppDatabase] instance. Hilt uses this function to inject
     * DAO wherever it is required.
     *
     * @param database The [AppDatabase] instance holding the DAOs.
     * @return The DAOs interfaces for database operations related to users.
     */


    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideExerciseDao(database: AppDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    fun provideSleepDao(database: AppDatabase): SleepDao {
        return database.sleepDao()
    }
}