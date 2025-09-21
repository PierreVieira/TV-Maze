package org.pierre.feature.search.warning.delete_all.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.feature.search.warning.delete_all.domain.usecase.DeleteAllSearchHistory
import org.pierre.feature.search.warning.delete_all.domain.usecase.impl.DeleteAllSearchHistoryUseCase

val deleteAllSearchDomainModule = module {
    factoryOf(::DeleteAllSearchHistoryUseCase).bind<DeleteAllSearchHistory>()
}
