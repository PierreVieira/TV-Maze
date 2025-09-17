package org.pierre.tvmaze.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.pierre.tvmaze.core.preferences.ThemePreference
import org.pierre.tvmaze.feature.theme_selection.domain.GetThemeOptionFlowUseCase

class AppViewModel(
    getThemeOptionFlow: GetThemeOptionFlowUseCase,
): ViewModel() {
    val themeState: StateFlow<ThemePreference> =
        getThemeOptionFlow()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ThemePreference.SYSTEM
            )
}
