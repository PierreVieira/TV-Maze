package org.pierre.tvmaze.search.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.search.domain.repository.SearchBarRepository
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.search.data.mapper.SearchHistoryItemMapper
import org.pierre.tvmaze.search.data.mapper.SearchPositionPreferencesMapper
import org.pierre.tvmaze.search.data.mapper.impl.SearchHistoryItemMapperImpl
import org.pierre.tvmaze.search.data.mapper.impl.SearchPositionPreferencesMapperImpl
import org.pierre.tvmaze.search.data.repository.SearchBarRepositoryImpl
import org.pierre.tvmaze.search.data.repository.SearchRepositoryImpl

val searchDataModule = module {
    singleOf(::SearchRepositoryImpl).bind<SearchRepository>()
    factoryOf(::SearchBarRepositoryImpl).bind<SearchBarRepository>()
    factoryOf(::SearchPositionPreferencesMapperImpl).bind<SearchPositionPreferencesMapper>()
    factoryOf(::SearchHistoryItemMapperImpl).bind<SearchHistoryItemMapper>()
}
