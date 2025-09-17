package org.pierre.tvmaze.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.presentation.AppViewModel

val appModule = module {
    viewModelOf(::AppViewModel)
}
