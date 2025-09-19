package org.pierre.tvmaze.switch_android_color_scheme.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.switch_android_color_scheme.presentation.viewmodel.AndroidColorSchemeViewModel

val switchAndroidColorSchemePresentationModule = module {
    viewModelOf(::AndroidColorSchemeViewModel)
}
