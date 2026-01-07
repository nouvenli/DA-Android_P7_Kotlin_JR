package com.openclassrooms.arista.domain.model

import java.time.LocalDateTime


/**
 * Represents a sleep session with its start time, duration, and quality rating.
 *
 * This data class is used within the domain layer to model a user's sleep record.
 *
 * @property startTime The date and time when the sleep session began.
 * @property duration The duration of the sleep session in minutes.
 * @property quality An integer rating representing the quality of sleep (e.g., on a scale of 1-5 or 1-10).
 */
data class Sleep(
    val startTime: LocalDateTime,
    val duration: Int,
    val quality: Int
)
