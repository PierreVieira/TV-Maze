package org.pierre.tvmaze.switch_android_color_scheme.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.switch_android_color_scheme.data.repository.AndroidColorSchemeRepositoryImpl
import org.pierre.tvmaze.switch_android_color_scheme.domain.repository.AndroidColorSchemeRepository

val switchAndroidColorSchemeDataModule = module {
    singleOf(::AndroidColorSchemeRepositoryImpl).bind<AndroidColorSchemeRepository>()
}
