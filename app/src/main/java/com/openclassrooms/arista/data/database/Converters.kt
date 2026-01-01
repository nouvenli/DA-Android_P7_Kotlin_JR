package com.openclassrooms.arista.data.database

import androidx.room.TypeConverter
import com.openclassrooms.arista.domain.model.ExerciseCategory

class Converters {

    // enum to String pour la BDD
    @TypeConverter
    fun fromCategory(category: ExerciseCategory): String {
        return category.name
    }

    // String to enum pour kotlin
    @TypeConverter
    fun toCategory(value: String): ExerciseCategory {
        return try {
            ExerciseCategory.valueOf(value)
        } catch (e: IllegalArgumentException) {
            // Valeur par défaut si la conversion échoue
            ExerciseCategory.Running
        }
    }
}