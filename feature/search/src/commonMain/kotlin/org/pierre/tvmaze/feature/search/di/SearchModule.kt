package org.pierre.tvmaze.feature.search.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.feature.search.presentation.viewmodel.SearchViewModel

val searchModule = module {
    viewModelOf(::SearchViewModel)
}
