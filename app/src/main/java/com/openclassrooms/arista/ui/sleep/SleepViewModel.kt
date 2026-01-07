package com.openclassrooms.arista.ui.sleep


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/**
 * ViewModel dedicated to the sleep management screen.
 *
 * This class is responsible for preparing and managing the data for the UI related to sleep history.
 * It interacts with the domain layer through the [GetAllSleepsUseCase] to fetch the list of sleep sessions.
 *
 * The data is exposed via a [StateFlow] which emits a list of [Sleep] objects. This flow is
 * lifecycle-aware and will stop collecting data 5 seconds after the UI is no longer subscribed.
 *
 * @property sleeps A [StateFlow] containing the list of sleep sessions. Emits an empty list initially or in case of an error.
 * @param getAllSleepsUseCase The use case used to retrieve sleep data from the repository.
 */
@HiltViewModel
class SleepViewModel @Inject constructor(
    getAllSleepsUseCase: GetAllSleepsUseCase
) : ViewModel() {

    // Transformation directe du flux
    val sleeps: StateFlow<List<Sleep>> = getAllSleepsUseCase.execute()
        .catch { exception ->
            exception.printStackTrace()
            emit(emptyList())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
