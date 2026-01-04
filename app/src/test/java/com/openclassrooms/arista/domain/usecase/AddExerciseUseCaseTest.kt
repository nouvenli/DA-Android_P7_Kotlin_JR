package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
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
class AddExerciseUseCaseTest {

    @Mock
    private lateinit var exerciseRepository: ExerciseRepository

    private lateinit var addExerciseUseCase: AddExerciseUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        addExerciseUseCase = AddExerciseUseCase(exerciseRepository)
    }

    @Test
    fun `Successful exercise addition`() = runBlocking {
        val expectedExercise = Exercise(
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 2
        )

        addExerciseUseCase.execute(expectedExercise)

        Mockito.verify(exerciseRepository, Mockito.times(1)).addExercise(expectedExercise)
    }

    @Test(expected = Exception::class)
    fun `Repository exception propagation`() = runBlocking {
        val exercice = Exercise(
            startTime = LocalDateTime.now(),
            duration = 10,
            category = ExerciseCategory.Swimming,
            intensity = 2
        )
        Mockito.`when`(exerciseRepository.addExercise(exercice))
            .thenThrow(Exception("Repository error"))

        addExerciseUseCase.execute(exercice)
    }


    @Test
    fun `When execute() is called with invalid data fields the repository's add exercise method should be called`() =
        runBlocking {
        val invalidExercise = Exercise(
            startTime = LocalDateTime.now(),
            duration = -3,
            category = ExerciseCategory.Swimming,
            intensity = 2
        )
        addExerciseUseCase.execute(invalidExercise)

        Mockito.verify(exerciseRepository, Mockito.times(1)).addExercise(invalidExercise)

        }
}
