package com.openclassrooms.arista.data.mapper

import com.openclassrooms.arista.data.database.entities.SleepEntity
import com.openclassrooms.arista.domain.model.Sleep
import java.time.Instant
import java.time.ZoneId

/**
 * Converts a [SleepEntity] (Data Transfer Object) from the database layer to a [Sleep] domain model.
 *
 * This mapping process involves:
 * - Transforming the start time from milliseconds (Epoch) to a `LocalDateTime` object using the system's default time zone.
 * - Mapping the sleep duration and quality rating directly.
 *
 * @return A [Sleep] object representing the domain model of the sleep session.
 */// Dto to domain
fun SleepEntity.toDomain(): Sleep {
    return Sleep(
        startTime = Instant.ofEpochMilli(this.startTime).atZone(ZoneId.systemDefault())
            .toLocalDateTime(),
        duration = this.duration,
        quality = this.quality
    )
}

//Domain to Dto
fun Sleep.toEntity(): SleepEntity {
    return SleepEntity(
        id = 0,
        startTime = this.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        duration = this.duration,
        quality = this.quality
    )
}