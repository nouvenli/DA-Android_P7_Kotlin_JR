package com.openclassrooms.arista.domain.model

import java.time.LocalDateTime

/**
 * Represents a physical exercise session performed by a user.
 *
 * This data class is a domain model used to encapsulate the details of a specific workout,
 * including its timing, duration, type, and perceived intensity.
 *
 * @property id The unique identifier for the exercise. It can be null if the exercise hasn't been persisted yet.
 * @property startTime The specific date and time when the exercise started.
 * @property duration The duration of the exercise in minutes.
 * @property category The type of exercise performed (e.g., Running, Swimming, Yoga), defined by [ExerciseCategory].
 * @property intensity An integer value representing the intensity level of the workout (typically on a scale, e.g., 1-10).
 */
data class Exercise(
    val id: Long? = null,
    val startTime: LocalDateTime,
    val duration: Int,
    val category: ExerciseCategory, //enum
    val intensity: Int
)