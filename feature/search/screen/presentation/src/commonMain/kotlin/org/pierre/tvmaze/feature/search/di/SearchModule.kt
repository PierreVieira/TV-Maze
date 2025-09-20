package org.pierre.tvmaze.feature.search.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconModelsFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchHistoryContentFactory
import org.pierre.tvmaze.feature.search.presentation.factory.impl.InitialSearchStateFactoryImpl
import org.pierre.tvmaze.feature.search.presentation.factory.impl.SearchBarIconModelsFactoryImpl
import org.pierre.tvmaze.feature.search.presentation.factory.impl.SearchBarIconsFactoryImpl
import org.pierre.tvmaze.feature.search.presentation.factory.impl.SearchHistoryContentFactoryImpl
import org.pierre.tvmaze.feature.search.presentation.viewmodel.SearchViewModel

val searchPresentationModule = module {
    viewModelOf(::SearchViewModel)
    factoryOf(::InitialSearchStateFactoryImpl).bind<InitialSearchStateFactory>()
    factoryOf(::SearchBarIconsFactoryImpl).bind<SearchBarIconsFactory>()
    factoryOf(::SearchBarIconModelsFactoryImpl).bind<SearchBarIconModelsFactory>()
    factoryOf(::SearchHistoryContentFactoryImpl).bind<SearchHistoryContentFactory>()
}
