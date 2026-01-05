package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class GetAllExercisesUseCaseTest {

    @Mock
    private lateinit var exerciseRepository: ExerciseRepository

    private lateinit var getAllExercisesUseCase: GetAllExercisesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getAllExercisesUseCase = GetAllExercisesUseCase(exerciseRepository)
    }

    @Test
    fun `execute() should  be return a liste of exercise`() = runBlocking {
        val expectedExercises = listOf(
            Exercise(
                startTime = LocalDateTime.now(),
                duration = 30,
                category = ExerciseCategory.Running,
                intensity = 2
            ),
            Exercise(
                startTime = LocalDateTime.now().plusHours(1),
                duration = 45,
                category = ExerciseCategory.Riding,
                intensity = 3
            )
        )

        Mockito.`when`(exerciseRepository.getAllExercises()).thenReturn(flowOf(expectedExercises))

        val resultFlow = getAllExercisesUseCase.execute()

        val result = resultFlow.first()

        assertEquals(expectedExercises, result)
    }

    @Test
    fun `execute() should  be return an empty list when no exercise exist`() = runBlocking {

        val emptyExerciseList = emptyList<Exercise>()

        Mockito.`when`(exerciseRepository.getAllExercises()).thenReturn(flowOf(emptyList()))

        val resultFlow = getAllExercisesUseCase.execute()

        val result = resultFlow.first()

        assertEquals(emptyExerciseList, result)
        assert(result.isEmpty())

    }
}