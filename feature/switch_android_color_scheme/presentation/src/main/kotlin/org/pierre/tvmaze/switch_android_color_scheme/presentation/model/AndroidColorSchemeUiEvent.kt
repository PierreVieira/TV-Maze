package org.pierre.tvmaze.switch_android_color_scheme.presentation.model

sealed interface AndroidColorSchemeUiEvent {
    data class OnIsDynamicColorsEnabledChange(val isEnabled: Boolean) : AndroidColorSchemeUiEvent
    data object OnInformationIconClick : AndroidColorSchemeUiEvent
    data object OnInformationDialogDismiss : AndroidColorSchemeUiEvent
    data object BottomSheetGotItClick: AndroidColorSchemeUiEvent
}
