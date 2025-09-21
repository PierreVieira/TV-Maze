package org.pierre.feature.search.warning.delete_all.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.pierre.feature.search.warning.delete_all.domain.usecase.DeleteAllSearchHistory
import org.pierre.feature.search.warning.delete_all.presentation.model.DeleteAllSearchUiEvent

class DeleteAllSearchViewModel(
    private val deleteAllSearchHistory: DeleteAllSearchHistory,
): ViewModel() {

    private val _navigateBackUiAction: Channel<Unit> = Channel()
    val navigateBackUiAction: Flow<Unit> = _navigateBackUiAction.receiveAsFlow()

    fun onEvent(uiEvent: DeleteAllSearchUiEvent) {
        viewModelScope.launch {
            when (uiEvent) {
                DeleteAllSearchUiEvent.CONFIRM -> {
                    deleteAllSearchHistory()
                    navigateBack()
                }
                DeleteAllSearchUiEvent.CANCEL,
                DeleteAllSearchUiEvent.DISMISS -> navigateBack()
            }
        }
    }

    private suspend fun navigateBack() {
        _navigateBackUiAction.send(Unit)
    }
}
