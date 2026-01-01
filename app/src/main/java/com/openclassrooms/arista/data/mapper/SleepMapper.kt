package com.openclassrooms.arista.data.mapper

import com.openclassrooms.arista.data.database.entities.SleepDto
import com.openclassrooms.arista.domain.model.Sleep
import java.time.Instant
import java.time.ZoneId

// Dto to domaine
fun SleepDto.toDomain(): Sleep {
    return Sleep(
        startTime = Instant.ofEpochMilli(this.startTime).atZone(ZoneId.systemDefault())
            .toLocalDateTime(),
        duration = this.duration,
        quality = this.quality
    )
}

//Domaine to Dto
fun Sleep.toDto(): SleepDto {
    return SleepDto(
        id = 0,
        startTime = this.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        duration = this.duration,
        quality = this.quality
    )

}