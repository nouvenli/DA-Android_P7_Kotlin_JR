package com.openclassrooms.arista.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * ViewModel responsible for managing and providing user data to the UI.
 *
 * This class interacts with the domain layer through [GetAllUsersUseCase] to fetch user information.
 * It exposes the user state as a [StateFlow] which emits the first user found in the repository,
 * or null if no user is available.
 *
 * @property getAllUsersUseCase The use case used to retrieve the list of users.
 */
@HiltViewModel
class UserDataViewModel @Inject constructor(private val getAllUsersUseCase: GetAllUsersUseCase) :
    ViewModel() {
    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow: StateFlow<User?> = _userFlow.asStateFlow()

    init {
        loadUserData()
    }

    /**
     * Initiates the retrieval of user data from the domain layer.
     *
     * This function launches a coroutine within the [viewModelScope] to collect the flow
     * of users provided by [GetAllUsersUseCase]. Upon receiving the list of users, it updates
     * the [_userFlow] state with the first user found in the list, or null if the list is empty.
     * Any exceptions occurring during the collection are caught and logged to the stack trace.
     */
    private fun loadUserData() {
        //collect au sein d'une coroutine
        viewModelScope.launch {
            // abonnement au flux du usecase avec collect
            getAllUsersUseCase.execute()
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect { userList ->
                _userFlow.value = userList.firstOrNull()
            }
        }
    }
}
