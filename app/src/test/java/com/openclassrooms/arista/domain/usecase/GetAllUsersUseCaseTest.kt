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
class GetAllUsersUseCaseTest {

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var getAllUsersUseCase: GetAllUsersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getAllUsersUseCase = GetAllUsersUseCase(userRepository)
    }

    // Ajoutez les tests

    @Test
    fun `execute() should return a list of users in flow`() = runBlocking {
        val expectedUsers = listOf(
            User(name = "Alice", email = "alice@example.com"),
            User(name = "Bob", email = "bob@example.com")
        )

        Mockito.`when`(userRepository.getAllUsers()).thenReturn(flowOf(expectedUsers))

        val resultFlow = getAllUsersUseCase.execute()

        val result = resultFlow.first()

        TestCase.assertEquals(expectedUsers, result)
    }

    @Test
    fun `execute() should return an empty list when no users are found`() = runBlocking {
        val emptyUserList = emptyList<User>()

        Mockito.`when`(userRepository.getAllUsers()).thenReturn(flowOf(emptyUserList))

        val resultFlow = getAllUsersUseCase.execute()

        val result = resultFlow.first()

        TestCase.assertEquals(emptyUserList, result)
        assert(result.isEmpty())

    }

}