package com.openclassrooms.arista.domain.model

import java.time.LocalDateTime


data class Sleep(
    val startTime: LocalDateTime,
    val duration: Int,
    val quality: Int
)
