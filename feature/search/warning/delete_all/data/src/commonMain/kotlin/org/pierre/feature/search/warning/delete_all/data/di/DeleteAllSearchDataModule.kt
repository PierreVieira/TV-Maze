package org.pierre.feature.search.warning.delete_all.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.feature.search.warning.delete_all.data.repository.DeleteAllSearchRepositoryImpl
import org.pierre.feature.search.warning.delete_all.domain.repository.DeleteAllSearchRepository

val deleteAllSearchDataModule = module {
    singleOf(::DeleteAllSearchRepositoryImpl).bind<DeleteAllSearchRepository>()
}
