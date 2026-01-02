package com.openclassrooms.arista.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.usecase.AddExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addExerciseUseCase: AddExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {

    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()

    init {
        loadAllExercises()
    }

    private fun loadAllExercises() {
        viewModelScope.launch {
            getAllExercisesUseCase.execute().collect { exercises ->
                _exercisesFlow.value = exercises
            }
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            deleteExerciseUseCase.execute(exercise)
        }
    }

    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch {
            addExerciseUseCase.execute(exercise)
        }
    }
}
