package com.openclassrooms.arista.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.openclassrooms.arista.data.database.dao.ExerciseDao
import com.openclassrooms.arista.data.database.dao.SleepDao
import com.openclassrooms.arista.data.database.dao.UserDao
import com.openclassrooms.arista.data.database.entities.ExerciseDto
import com.openclassrooms.arista.data.database.entities.SleepDto
import com.openclassrooms.arista.data.database.entities.UserDto

@Database(entities = [UserDto::class, ExerciseDto::class, SleepDto::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun sleepDao(): SleepDao
    }