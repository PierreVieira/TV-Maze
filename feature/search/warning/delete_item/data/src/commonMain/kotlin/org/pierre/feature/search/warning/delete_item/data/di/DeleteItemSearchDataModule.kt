package org.pierre.feature.search.warning.delete_item.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.feature.search.warning.delete_item.data.repository.DeleteItemSearchRepositoryImpl
import org.pierre.feature.search.warning.delete_item.domain.repository.DeleteItemSearchRepository

val deleteItemSearchDataModule = module {
    factoryOf(::DeleteItemSearchRepositoryImpl).bind<DeleteItemSearchRepository>()
}
