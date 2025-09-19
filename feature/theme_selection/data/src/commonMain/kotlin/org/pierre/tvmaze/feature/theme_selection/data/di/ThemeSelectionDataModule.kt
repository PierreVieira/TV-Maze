package org.pierre.tvmaze.feature.theme_selection.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.theme_selection.data.mapper.ThemePreferenceMapper
import org.pierre.tvmaze.feature.theme_selection.data.mapper.ThemePreferenceMapperImpl
import org.pierre.tvmaze.feature.theme_selection.data.repository.ThemeSelectionRepositoryImpl
import org.pierre.tvmaze.feature.theme_selection.domain.repository.ThemeSelectionRepository

val themeSelectionDataModule = module {
    factoryOf(::ThemeSelectionRepositoryImpl).bind<ThemeSelectionRepository>()
    factoryOf(::ThemePreferenceMapperImpl).bind<ThemePreferenceMapper>()
}
