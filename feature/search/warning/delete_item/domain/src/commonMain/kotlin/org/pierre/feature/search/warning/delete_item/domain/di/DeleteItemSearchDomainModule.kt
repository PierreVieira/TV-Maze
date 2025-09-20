package org.pierre.feature.search.warning.delete_item.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.feature.search.warning.delete_item.domain.usecase.DeleteSearchHistoryItem
import org.pierre.feature.search.warning.delete_item.domain.usecase.impl.DeleteSearchHistoryItemUseCase

val deleteItemSearchDomainModule = module {
    factoryOf(::DeleteSearchHistoryItemUseCase).bind<DeleteSearchHistoryItem>()
}
