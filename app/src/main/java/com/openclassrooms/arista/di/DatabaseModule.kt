package com.openclassrooms.arista.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassrooms.arista.data.database.AppDatabase
import com.openclassrooms.arista.data.database.dao.ExerciseDao
import com.openclassrooms.arista.data.database.dao.SleepDao
import com.openclassrooms.arista.data.database.dao.UserDao
import com.openclassrooms.arista.data.database.entities.ExerciseDto
import com.openclassrooms.arista.data.database.entities.UserDto
import com.openclassrooms.arista.data.database.entities.SleepDto
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

/*
* Module de Hilt pour la base de données
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        provider: Provider<AppDatabase>
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Insérer les données par défaut ici
                    CoroutineScope(Dispatchers.IO).launch {
                        val database = provider.get()
                        val userDao = database.userDao()
                        val exerciseDao = database.exerciseDao()
                        val sleepDao = database.sleepDao()

                        populateDatabase(userDao, exerciseDao, sleepDao)
                    }
                }

            })
            .build()
    }

    /*
    * fonction pour remplir la base de données avec des données par défaut
    * à l'instanciation de la base de données
     */
suspend fun populateDatabase(userDao: UserDao, exerciseDao: ExerciseDao, sleepDao: SleepDao) {
        userDao.insertUser(
            UserDto(
                name="AnneOnyme",
                email="AnneOnyme@example.com"
            ))

        exerciseDao.insertExercise(
            ExerciseDto(
                startTime=LocalDateTime.now().minusHours(5).atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                duration=30,
                category=ExerciseCategory.Running,
                intensity=7))
        exerciseDao.insertExercise(
            ExerciseDto(
                startTime=LocalDateTime.now().minusDays(1).minusHours(3).atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                duration=45,
                category=ExerciseCategory.Swimming,
                intensity=6))
        exerciseDao.insertExercise(
            ExerciseDto(
                startTime= LocalDateTime.now().minusDays(2).minusHours(4).atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                duration=60,
                category=ExerciseCategory.Football,
                intensity=8))

        // correction de la duration en x60 ça me paraissait bizarre des micros siestes
        sleepDao.insertSleep(
            SleepDto(
                startTime = LocalDateTime.now().minusDays(1).atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                duration = 420,
                quality = 8
            )
        )
        sleepDao.insertSleep(
            SleepDto(
                startTime = LocalDateTime.now().minusDays(2).atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                duration = 360,
                quality = 5
            )
        )
        sleepDao.insertSleep(
            SleepDto(
                startTime = LocalDateTime.now().minusDays(3).atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                duration = 480,
                quality = 9
            )
        )
}


    /*
    * fourniture des Daos à travers Hilt
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