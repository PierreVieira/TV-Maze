package org.pierre.tvmaze.feature.theme_selection.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.feature.theme_selection.presentation.ThemeSelectionViewModel

val themeSelectionPresentationModule: Module = module {
    viewModelOf(::ThemeSelectionViewModel)
}
