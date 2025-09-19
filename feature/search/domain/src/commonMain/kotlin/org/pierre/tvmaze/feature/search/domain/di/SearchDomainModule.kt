package org.pierre.tvmaze.feature.search.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.search.domain.usecase.SearchUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.SearchUseCaseImpl

val searchDomainModule = module {
    factoryOf(::SearchUseCaseImpl).bind<SearchUseCase>()
}
