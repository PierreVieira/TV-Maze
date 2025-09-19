package org.pierre.tvmaze.feature.theme_selection.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.GetThemeOptionFlow
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.SetThemeOption
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl.GetThemeOptionFlowUseCase
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl.SetThemeOptionUseCase

val themeSelectionDomainModule = module {
    factoryOf(::GetThemeOptionFlowUseCase).bind<GetThemeOptionFlow>()
    factoryOf(::SetThemeOptionUseCase).bind<SetThemeOption>()
}
