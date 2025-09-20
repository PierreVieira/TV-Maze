package org.pierre.feature.search.warning.delete_item.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.pierre.feature.search.warning.delete_item.domain.usecase.DeleteSearchHistoryItem
import org.pierre.feature.search.warning.delete_item.presentation.model.DeleteSearchItemDialogRoute
import org.pierre.feature.search.warning.delete_item.presentation.model.DeleteSearchItemUiEvent

class DeleteSearchItemViewModel(
    savedStateHandle: SavedStateHandle,
    private val deleteSearchHistoryItem: DeleteSearchHistoryItem,
): ViewModel() {
    private val item = savedStateHandle.toRoute<DeleteSearchItemDialogRoute>()
    val itemName = item.name

    private val _navigateBackUiAction: Channel<Unit> = Channel()
    val navigateBackUiAction: Flow<Unit> = _navigateBackUiAction.receiveAsFlow()

    fun onEvent(uiEvent: DeleteSearchItemUiEvent) {
        viewModelScope.launch {
            when (uiEvent) {
                DeleteSearchItemUiEvent.CONFIRM -> {
                    deleteSearchHistoryItem(item.id)
                    navigateBack()
                }
                DeleteSearchItemUiEvent.CANCEL,
                DeleteSearchItemUiEvent.DISMISS -> navigateBack()
            }
        }
    }

    private suspend fun navigateBack() {
        _navigateBackUiAction.send(Unit)
    }
}
