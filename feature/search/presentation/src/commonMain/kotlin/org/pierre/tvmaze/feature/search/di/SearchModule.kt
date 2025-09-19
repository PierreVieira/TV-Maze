package org.pierre.tvmaze.feature.search.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.search.data.repository.SearchRepositoryImpl
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.factory.impl.InitialSearchStateFactoryImpl
import org.pierre.tvmaze.feature.search.presentation.factory.impl.SearchBarIconsFactoryImpl
import org.pierre.tvmaze.feature.search.presentation.viewmodel.SearchViewModel

val searchPresentationModule = module {
    viewModelOf(::SearchViewModel)
    factoryOf(::InitialSearchStateFactoryImpl).bind<InitialSearchStateFactory>()
    factoryOf(::SearchBarIconsFactoryImpl).bind<SearchBarIconsFactory>()
    factoryOf(::SearchRepositoryImpl).bind<SearchRepository>()
}
