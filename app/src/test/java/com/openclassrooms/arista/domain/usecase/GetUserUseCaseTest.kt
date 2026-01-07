package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import junit.framework.TestCase
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

@RunWith(JUnit4::class)
class GetUserUseCaseTest {

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getUserUseCase = GetUserUseCase(userRepository)
    }

    // Ajoutez les tests

    @Test
    fun `execute() should return a single user in flow`() = runBlocking {
        val expectedUser = User(name = "Alice", email = "alice@example.com")

        Mockito.`when`(userRepository.getUser()).thenReturn(flowOf(expectedUser))

        val resultFlow = getUserUseCase.execute()

        val result = resultFlow.first()
        TestCase.assertEquals(expectedUser, result)
    }

    @Test
    fun `execute() should return null when no user is found`() = runBlocking {
        val emptyUserList = emptyList<User>()

        Mockito.`when`(userRepository.getUser()).thenReturn(flowOf(null))

        val resultFlow = getUserUseCase.execute()

        val result = resultFlow.first()
        TestCase.assertNull(result)
    }

}
