package com.openclassrooms.arista.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "user")
data class UserDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUser")
    val idUser: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "email")
    val email: String

)
