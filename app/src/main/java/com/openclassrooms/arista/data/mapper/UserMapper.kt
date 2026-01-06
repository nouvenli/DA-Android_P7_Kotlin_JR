package com.openclassrooms.arista.data.mapper
import com.openclassrooms.arista.data.database.entities.UserDto

import com.openclassrooms.arista.domain.model.User

// Dto to Domain
fun UserDto.toDomain(): User {
    return User(
        id=this.idUser,
        name = this.name,
        email = this.email
    )
}

// Domaine to le Dto
fun User.toDto(): UserDto {
    return UserDto(
        idUser = this.id,
        name = this.name,
        email = this.email
    )
}
