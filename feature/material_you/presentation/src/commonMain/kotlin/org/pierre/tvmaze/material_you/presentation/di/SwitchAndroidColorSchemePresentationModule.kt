package org.pierre.tvmaze.material_you.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.material_you.presentation.viewmodel.AndroidColorSchemeViewModel

val switchAndroidColorSchemePresentationModule = module {
    viewModelOf(::AndroidColorSchemeViewModel)
}
