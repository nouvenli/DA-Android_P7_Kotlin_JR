package com.openclassrooms.arista.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


/**
 * Represents an Sleep entry in the application's local database.
 *
 * This class defines the structure of the "sleep" table in the Room database,
 * storing information about a specific sleep session.
 *
 * @property id The unique identifier for the sleep record (auto-generated).
 * @property startTime The timestamp in milliseconds representing when the user went to sleep.
 * @property duration The duration of the sleep session in minutes.
 * @property quality A numerical rating indicating the quality of sleep (e.g., 1 to 5).
 */
@Entity(tableName = "sleep")
data class SleepEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "start_time")
    val startTime: Long,

    @ColumnInfo(name = "duration")
    val duration: Int,

    @ColumnInfo(name = "quality")
    val quality: Int
)