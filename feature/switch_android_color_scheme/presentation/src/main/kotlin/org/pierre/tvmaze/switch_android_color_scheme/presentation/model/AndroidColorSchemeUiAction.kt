package org.pierre.tvmaze.switch_android_color_scheme.presentation.model

sealed interface AndroidColorSchemeUiAction {
    data object NavigateToMaterialYouBottomSheet : AndroidColorSchemeUiAction
    data object CloseBottomSheet : AndroidColorSchemeUiAction
}
