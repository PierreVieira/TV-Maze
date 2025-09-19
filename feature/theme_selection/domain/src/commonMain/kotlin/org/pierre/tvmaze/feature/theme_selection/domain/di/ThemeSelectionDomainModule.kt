package org.pierre.tvmaze.feature.theme_selection.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.GetThemeOptionFlowUseCase
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.SetThemeOptionUseCase
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl.GetThemeOptionFlow
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl.SetThemeOption

val themeSelectionDomainModule = module {
    factoryOf(::GetThemeOptionFlow).bind<GetThemeOptionFlowUseCase>()
    factoryOf(::SetThemeOption).bind<SetThemeOptionUseCase>()
}
