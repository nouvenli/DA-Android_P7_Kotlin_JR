package com.openclassrooms.arista.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/**
 * ViewModel responsible for managing and providing user data to the UI.
 *
 * This class interacts with the domain layer through [GetUserUseCase] to fetch user information.
 * It exposes the user state as a [StateFlow].
 */
@HiltViewModel
class UserDataViewModel @Inject constructor(
    getUserUseCase: GetUserUseCase
) : ViewModel() {

    /**
     * Exposes the current user state.
     * Use stateIn to convert the Flow from the UseCase into a StateFlow efficiently.
     */
    val userFlow: StateFlow<User?> = getUserUseCase.execute()
        .catch { exception ->
            exception.printStackTrace()
            emit(null)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}