package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
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
class GetAllSleepsUseCaseTest {

    @Mock
    private lateinit var sleepRepository: SleepRepository

    private lateinit var getAllSleepsUseCase: GetAllSleepsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getAllSleepsUseCase = GetAllSleepsUseCase(sleepRepository)
    }

    @Test
    fun `execute() should  be return a liste of sleep`() = runBlocking {
        val expectedSleeps = listOf(
            Sleep(startTime = LocalDateTime.now(), duration = 180, quality = 2),
            Sleep(startTime = LocalDateTime.now().plusHours(1), duration = 260, quality = 5)
        )
        Mockito.`when`(sleepRepository.getAllSleeps()).thenReturn(flowOf(expectedSleeps))

        val resultFlow = getAllSleepsUseCase.execute()

        val result = resultFlow.first()

        assertEquals(expectedSleeps, result)

    }

    @Test
    fun `when execute() should return an empty list when no sleep exist`() =runBlocking {
        val emptySleepList = emptyList<Sleep>()

        Mockito.`when`(sleepRepository.getAllSleeps()).thenReturn(flowOf(emptySleepList))

        val resultFlow = getAllSleepsUseCase.execute()

        val result = resultFlow.first()

        assertEquals(emptySleepList, result)
        assert(result.isEmpty())
    }
}