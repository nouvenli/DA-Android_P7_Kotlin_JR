package com.openclassrooms.arista.domain.model

/**
 * Represents a user of the application.
 *
 * This data class holds the essential information for a user, including their unique identifier, name, and email address.
 * It is a core model within the domain layer.
 *
 * @property id The unique identifier for the user. Defaults to 0 for new users not yet persisted.
 * @property name The full name of the user.
 * @property email The email address of the user.
 */
data class User(
    val id: Long = 0,
    val name: String,
    val email: String
)