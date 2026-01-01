package com.openclassrooms.arista.domain.model

import java.time.LocalDateTime

data class Exercise(
    val id: Long? = null,
    val startTime: LocalDateTime,
    val duration: Int,
    val category: ExerciseCategory, //enum
    val intensity: Int
)