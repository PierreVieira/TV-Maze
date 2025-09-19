package org.pierre.tvmaze.feature.search.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.search.domain.model.SearchUseCases
import org.pierre.tvmaze.feature.search.domain.usecase.GetDurationDisappearMenuDuration
import org.pierre.tvmaze.feature.search.domain.usecase.GetErrorTypeFromThrowable
import org.pierre.tvmaze.feature.search.domain.usecase.Search
import org.pierre.tvmaze.feature.search.domain.usecase.GetNewSearchBarPositionDueToToggle
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchBarPositionFlow
import org.pierre.tvmaze.feature.search.domain.usecase.SaveNewSearchBarPosition
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetDurationDisappearMenuDurationUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetErrorTypeFromThrowableUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.SearchUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetNewSearchBarPositionDueToToggleUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetSearchBarPositionFlowUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.SaveNewSearchBarPositionUseCase

val searchDomainModule = module {
    factoryOf(::SearchUseCases)
    factoryOf(::SearchUseCase).bind<Search>()
    factoryOf(::GetNewSearchBarPositionDueToToggleUseCase).bind<GetNewSearchBarPositionDueToToggle>()
    factoryOf(::SaveNewSearchBarPositionUseCase).bind<SaveNewSearchBarPosition>()
    factoryOf(::GetSearchBarPositionFlowUseCase).bind<GetSearchBarPositionFlow>()
    factoryOf(::GetDurationDisappearMenuDurationUseCase).bind<GetDurationDisappearMenuDuration>()
    factoryOf(::GetErrorTypeFromThrowableUseCase).bind<GetErrorTypeFromThrowable>()
}
