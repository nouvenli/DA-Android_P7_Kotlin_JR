package com.openclassrooms.arista.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.openclassrooms.arista.domain.model.ExerciseCategory


@Entity(tableName = "exercise")
data class ExerciseDto(
    @PrimaryKey(autoGenerate = true)
@ColumnInfo(name = "id")
val id: Long = 0,

    @ColumnInfo(name = "start_time")
val startTime: Long,

    @ColumnInfo(name = "duration")
val duration: Int,

    @ColumnInfo(name = "category")
val category: ExerciseCategory, //enum

    @ColumnInfo(name = "intensity")
val intensity: Int
)