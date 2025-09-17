package org.pierre.tvmaze.feature.theme_selection.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.feature.theme_selection.domain.GetThemeOptionFlowUseCase
import org.pierre.tvmaze.feature.theme_selection.domain.SetThemeOptionUseCase
import org.pierre.tvmaze.feature.theme_selection.presentation.ThemeSelectionViewModel

val themeSelectionModule: Module = module {
    viewModelOf(::ThemeSelectionViewModel)
    factoryOf(::GetThemeOptionFlowUseCase)
    factoryOf(::SetThemeOptionUseCase)
}
