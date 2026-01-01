package com.openclassrooms.arista.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


@Entity(tableName = "sleep")
data class SleepDto(
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