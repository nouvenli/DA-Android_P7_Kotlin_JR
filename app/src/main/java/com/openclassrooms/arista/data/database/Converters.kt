package com.openclassrooms.arista.data.database

import androidx.room.TypeConverter
import com.openclassrooms.arista.domain.model.ExerciseCategory

/**
 * Type converters to allow Room to reference complex data types.
 *
 * This class provides methods to convert custom types [ExerciseCategory] to and from
 * types that Room can persist (like [String]).
 */
class Converters {

    // Enum to String
    @TypeConverter
    fun fromCategory(category: ExerciseCategory): String {
        return category.name
    }

    // String to enum
    @TypeConverter
    fun toCategory(value: String): ExerciseCategory {
        return try {
            ExerciseCategory.valueOf(value)
        } catch (e: IllegalArgumentException) {
            ExerciseCategory.Running
        }
    }
}