package com.openclassrooms.arista.data.mapper

import com.openclassrooms.arista.data.database.entities.UserEntity

import com.openclassrooms.arista.domain.model.User

// Entity to Domain
fun UserEntity.toDomain(): User {
    return User(
        id = this.idUser,
        name = this.name,
        email = this.email
    )
}

// Domain to entity
fun User.toEntity(): UserEntity {
    return UserEntity(
        idUser = this.id,
        name = this.name,
        email = this.email
    )
}
