package org.pierre.feature.search.warning.delete_all.presentation.model

sealed interface DeleteAllSearchUiEvent {
    data object CONFIRM: DeleteAllSearchUiEvent
    data object CANCEL: DeleteAllSearchUiEvent
    data object DISMISS: DeleteAllSearchUiEvent
}
