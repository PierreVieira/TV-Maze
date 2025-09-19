package org.pierre.tvmaze.switch_android_color_scheme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pierre.tvmaze.switch_android_color_scheme.domain.model.AndroidColorSchemeUseCases
import org.pierre.tvmaze.switch_android_color_scheme.presentation.model.AndroidColorSchemeUiAction
import org.pierre.tvmaze.switch_android_color_scheme.presentation.model.AndroidColorSchemeUiEvent
import org.pierre.tvmaze.ui.utils.observe

class AndroidColorSchemeViewModel(
    private val useCases: AndroidColorSchemeUseCases,
) : ViewModel() {

    private val _uiAction: Channel<AndroidColorSchemeUiAction> = Channel()
    val uiAction = _uiAction.receiveAsFlow()

    private val _uiState = MutableStateFlow(false)
    val isDynamicColorsOn: StateFlow<Boolean> = _uiState.asStateFlow()

    init {
        observe(useCases.getIsDynamicColorsEnabledFlow()) { isEnabled ->
            _uiState.update { isEnabled }
        }
    }

    fun onEvent(event: AndroidColorSchemeUiEvent) {
        viewModelScope.launch {
            when (event) {
                is AndroidColorSchemeUiEvent.OnIsDynamicColorsEnabledChange ->
                    useCases.setIsDynamicColorsEnabled(event.isEnabled)

                AndroidColorSchemeUiEvent.OnInformationIconClick ->
                    _uiAction.send(AndroidColorSchemeUiAction.NavigateToMaterialYouBottomSheet)

                AndroidColorSchemeUiEvent.OnInformationDialogDismiss,
                AndroidColorSchemeUiEvent.BottomSheetGotItClick,
                    -> _uiAction.send(AndroidColorSchemeUiAction.CloseBottomSheet)
            }
        }
    }
}
