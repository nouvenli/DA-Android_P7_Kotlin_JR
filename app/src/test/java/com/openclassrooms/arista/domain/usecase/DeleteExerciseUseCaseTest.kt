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
class DeleteExerciseUseCaseTest {

    @Mock
    private lateinit var exerciseRepository: ExerciseRepository

    private lateinit var deleteExerciseUseCase: DeleteExerciseUseCase


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        deleteExerciseUseCase = DeleteExerciseUseCase(exerciseRepository)
        }

    @Test
    fun `Successful exercise deletion`() = runBlocking {
        val exerciseToDelete = Exercise(
            id = 1,
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 2
        )

        deleteExerciseUseCase.execute(exerciseToDelete)

        Mockito.verify(exerciseRepository, Mockito.times(1)).deleteExercise(exerciseToDelete)
    }

    @Test(expected = Exception::class)
    fun `Repository exception propagation`() = runBlocking {
        val exerciseToDelete = Exercise(
            id = 1,
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 2
        )

        Mockito.`when`(exerciseRepository.deleteExercise(exerciseToDelete))
            .thenThrow(Exception("Repository error"))

        deleteExerciseUseCase.execute(exerciseToDelete)
    }
}
