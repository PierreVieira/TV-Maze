package org.pierre.tvmaze.core.koin_initializer.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.pierre.feature.search.warning.delete_item.data.di.deleteItemSearchDataModule
import org.pierre.feature.search.warning.delete_item.domain.di.deleteItemSearchDomainModule
import org.pierre.feature.search.warning.delete_item.presentation.di.deleteItemSearchPresentationModule
import org.pierre.tvmaze.core.data_store_provider.di.dataStoreProviderModule
import org.pierre.tvmaze.core.room_provider.di.roomModule
import org.pierre.tvmaze.feature.main.di.mainModule
import org.pierre.tvmaze.feature.search.di.searchPresentationModule
import org.pierre.tvmaze.feature.search.domain.di.searchDomainModule
import org.pierre.tvmaze.feature.theme_selection.data.di.themeSelectionDataModule
import org.pierre.tvmaze.feature.theme_selection.di.themeSelectionPresentationModule
import org.pierre.tvmaze.feature.theme_selection.domain.di.themeSelectionDomainModule
import org.pierre.tvmaze.mapper.di.mapperModule
import org.pierre.tvmaze.network.di.networkModule
import org.pierre.tvmaze.search.data.di.searchDataModule

fun commonKoinInitializer(
    extraModules: List<Module>,
    config: (KoinApplication.() -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        val coreModules = listOf(
            dataStoreProviderModule,
            mapperModule,
            networkModule,
            roomModule,
        )
        val featureModules = listOf(
            mainModule,
            searchDataModule,
            searchDomainModule,
            searchPresentationModule,
            deleteItemSearchDataModule,
            deleteItemSearchDomainModule,
            deleteItemSearchPresentationModule,
            themeSelectionDataModule,
            themeSelectionDomainModule,
            themeSelectionPresentationModule,
        )
        modules(coreModules + featureModules + extraModules)
    }
}
