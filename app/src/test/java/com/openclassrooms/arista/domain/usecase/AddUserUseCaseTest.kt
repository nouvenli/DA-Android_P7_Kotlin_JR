package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations



@RunWith(JUnit4::class)
class AddUserUseCaseTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var addUserUseCase: AddUserUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        addUserUseCase = AddUserUseCase(userRepository)
    }

    @Test
    fun `Successful user addition`() = runBlocking {
        val expectedUser = User(name = "Alice", email = "alice@test.com")

        addUserUseCase.execute(expectedUser)

        Mockito.verify(userRepository, Mockito.times(1)).addUser(expectedUser)
    }

    @Test(expected = Exception::class)
    fun `Repository exception propagation`() = runBlocking {
        val user = User(name = "Alice", email = "alice@test.com")

        Mockito.`when`(userRepository.addUser(user))
            .thenThrow(Exception("Repository error"))

        addUserUseCase.execute(user)
    }
}
