package com.openclassrooms.arista.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.usecase.AddExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

/**
 * ViewModel responsible for managing the UI data and logic related to exercises.
 *
 * This ViewModel serves as a bridge between the UI and the Domain layer.
 * It exposes a reactive stream of exercises and provides methods to perform operations like
 * adding or deleting exercises.
 *
 * It uses Hilt for dependency injection to acquire necessary Use Cases.
 *
 * @property getAllExercisesUseCase Use case to retrieve the list of all exercises.
 * @property addExerciseUseCase Use case to add a new exercise.
 * @property deleteExerciseUseCase Use case to remove an existing exercise.
 */
@HiltViewModel
class ExerciseViewModel @Inject constructor(
    getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addExerciseUseCase: AddExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {

    val exercisesFlow: StateFlow<List<Exercise>> = getAllExercisesUseCase.execute()
        .catch { exception ->
            exception.printStackTrace()
            emit(emptyList())
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    // error Flow for add and delete

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> = _errorFlow.asStateFlow()

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            try {
                deleteExerciseUseCase.execute(exercise)
            } catch (e: Exception) {
                e.printStackTrace()
                _errorFlow.value = "Error deleting exercise"

            }
        }
    }

    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch {
            try {
                addExerciseUseCase.execute(exercise)
            } catch (e: Exception) {
                e.printStackTrace()
                _errorFlow.value = "Error adding exercise"
            }
        }
    }
}
