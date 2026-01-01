package com.openclassrooms.arista.data.mapper
import com.openclassrooms.arista.data.database.entities.UserDto

import com.openclassrooms.arista.domain.model.User

/**
 * Dto to Domain
 */

fun UserDto.toDomain(): User {
    return User(
        name = this.name,
        email = this.email
    )
}

// Domaine to le Dto
fun User.toDto(): UserDto {
    return UserDto(
        idUser = 0,
        name = this.name,
        email = this.email
    )
}
