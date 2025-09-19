package org.pierre.tvmaze.feature.theme_selection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.pierre.core.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.SetThemeOptionUseCase
import org.pierre.tvmaze.feature.theme_selection.presentation.factory.ThemeOptionsFactory

class ThemeSelectionViewModel(
    private val setThemeOption: SetThemeOptionUseCase
): ViewModel() {

    val themeOptions = ThemeOptionsFactory.themeOptions

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            setThemeOption(theme)
        }
    }
}
