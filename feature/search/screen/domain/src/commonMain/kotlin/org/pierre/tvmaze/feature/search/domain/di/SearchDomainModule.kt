package org.pierre.tvmaze.feature.search.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.search.domain.model.SearchUseCases
import org.pierre.tvmaze.feature.search.domain.usecase.DeleteAllSearchHistory
import org.pierre.tvmaze.feature.search.domain.usecase.SaveRecentSearch
import org.pierre.tvmaze.feature.search.domain.usecase.EnforceSearchHistoryMaxSize
import org.pierre.tvmaze.feature.search.domain.usecase.GetCurrentTime
import org.pierre.tvmaze.feature.search.domain.usecase.GetDurationDisappearMenuDuration
import org.pierre.tvmaze.feature.search.domain.usecase.GetErrorTypeFromThrowable
import org.pierre.tvmaze.feature.search.domain.usecase.Search
import org.pierre.tvmaze.feature.search.domain.usecase.GetNewSearchBarPositionDueToToggle
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchBarPositionFlow
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchHistoryFlow
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchItemsLoading
import org.pierre.tvmaze.feature.search.domain.usecase.SaveNewSearchBarPosition
import org.pierre.tvmaze.feature.search.domain.usecase.impl.DeleteAllSearchHistoryUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.SaveRecentSearchUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.EnforceSearchHistoryMaxSizeUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetCurrentTimeUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetDurationDisappearMenuDurationUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetErrorTypeFromThrowableUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.SearchUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetNewSearchBarPositionDueToToggleUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetSearchBarPositionFlowUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetSearchHistoryFlowUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.GetSearchItemsLoadingUseCase
import org.pierre.tvmaze.feature.search.domain.usecase.impl.SaveNewSearchBarPositionUseCase

val searchDomainModule = module {
    factoryOf(::SearchUseCases)
    factoryOf(::SearchUseCase).bind<Search>()
    factoryOf(::GetNewSearchBarPositionDueToToggleUseCase).bind<GetNewSearchBarPositionDueToToggle>()
    factoryOf(::SaveNewSearchBarPositionUseCase).bind<SaveNewSearchBarPosition>()
    factoryOf(::GetSearchBarPositionFlowUseCase).bind<GetSearchBarPositionFlow>()
    factoryOf(::GetDurationDisappearMenuDurationUseCase).bind<GetDurationDisappearMenuDuration>()
    factoryOf(::GetErrorTypeFromThrowableUseCase).bind<GetErrorTypeFromThrowable>()
    factoryOf(::GetSearchItemsLoadingUseCase).bind<GetSearchItemsLoading>()
    factoryOf(::GetCurrentTimeUseCase).bind<GetCurrentTime>()
    factoryOf(::SaveRecentSearchUseCase).bind<SaveRecentSearch>()
    factoryOf(::EnforceSearchHistoryMaxSizeUseCase).bind<EnforceSearchHistoryMaxSize>()
    factoryOf(::GetSearchHistoryFlowUseCase).bind<GetSearchHistoryFlow>()
    factoryOf(::DeleteAllSearchHistoryUseCase).bind<DeleteAllSearchHistory>()
}
