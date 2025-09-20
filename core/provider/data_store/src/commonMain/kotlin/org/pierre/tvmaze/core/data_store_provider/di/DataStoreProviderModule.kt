package org.pierre.tvmaze.core.data_store_provider.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pierre.tvmaze.core.data_store_provider.createDataStore

val dataStoreProviderModule = module { singleOf(::createDataStore) }
