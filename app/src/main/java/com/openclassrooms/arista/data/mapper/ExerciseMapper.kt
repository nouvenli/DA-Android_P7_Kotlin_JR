package com.openclassrooms.arista.data.mapper

import com.openclassrooms.arista.data.database.entities.ExerciseEntity
import com.openclassrooms.arista.domain.model.Exercise
import java.time.Instant
import java.time.ZoneId

/**
 * Converts an [ExerciseEntity] from the database layer to an [Exercise] model
 * used in the domain layer.
 *
 * This mapping process involves:
 * - Transferring the ID, duration, category, and intensity directly.
 * - Converting the `startTime` from a Long (epoch milliseconds) to a [LocalDateTime] using the system's default time zone.
 *
 * @return An [Exercise] object representing the data from the [ExerciseEntity].
 */

// Dto to Domain
fun ExerciseEntity.toDomain(): Exercise {
    return Exercise(
        id = this.id,
        startTime = Instant.ofEpochMilli(this.startTime).atZone(ZoneId.systemDefault())
            .toLocalDateTime(),
        duration = this.duration,
        category = this.category,
        intensity = this.intensity
    )
}

// Domaine to Entity
fun Exercise.toEntity(): ExerciseEntity {
    return ExerciseEntity(
        id = this.id ?: 0, // if id is null, use 0 for a new creation
        startTime = this.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        duration = this.duration,
        category = this.category,
        intensity = this.intensity
    )
}