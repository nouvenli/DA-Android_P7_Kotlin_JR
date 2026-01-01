package com.openclassrooms.arista.data.mapper
import com.openclassrooms.arista.data.database.entities.ExerciseDto
import com.openclassrooms.arista.domain.model.Exercise
import java.time.Instant
import java.time.ZoneId

// Dto to Domain
fun ExerciseDto.toDomain() : Exercise {
    return Exercise(
        id = this.id,
        startTime = Instant.ofEpochMilli(this.startTime).atZone(ZoneId.systemDefault())
            .toLocalDateTime(),
        duration = this.duration,
        category = this.category,
        intensity = this.intensity
    )
}

// Domaine to Dto
fun Exercise.toDto(): ExerciseDto {
    return ExerciseDto(
        id = 0,
        startTime = this.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        duration = this.duration,
        category = this.category,
        intensity = this.intensity
        )
}