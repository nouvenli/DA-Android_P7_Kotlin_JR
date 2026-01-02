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


@HiltViewModel
class UserDataViewModel @Inject constructor(private val getAllUsersUseCase: GetAllUsersUseCase) :
    ViewModel() {
    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow: StateFlow<User?> = _userFlow.asStateFlow()

    init {
        loadUserData()
    }

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
