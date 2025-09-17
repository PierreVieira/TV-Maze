package org.pierre.tvmaze.feature.main.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.feature.main.presentation.MainScreenViewModel

val mainModule = module {
    viewModelOf(::MainScreenViewModel)
}
