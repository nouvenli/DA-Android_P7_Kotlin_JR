package com.openclassrooms.arista.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.openclassrooms.arista.data.database.dao.ExerciseDao
import com.openclassrooms.arista.data.database.dao.SleepDao
import com.openclassrooms.arista.data.database.dao.UserDao
import com.openclassrooms.arista.data.database.entities.ExerciseDto
import com.openclassrooms.arista.data.database.entities.SleepDto
import com.openclassrooms.arista.data.database.entities.UserDto

@Database(entities = [UserDto::class, ExerciseDto::class, SleepDto::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun sleepDao(): SleepDao

    /* Singleton supprimé - Test avec module Hilt
    companion object {
        /*
        * Volatile : one instance of AppDatabase
        * if not null return instance AppDatabase else NewInstance
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room
                    .databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .build()
                    .also{INSTANCE=it}

            }
        }*/
    }