package org.pierre.tvmaze.core.koin_initializer.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.pierre.tvmaze.core.data_store_provider.di.dataStoreProviderModule
import org.pierre.tvmaze.feature.main.di.mainModule
import org.pierre.tvmaze.feature.search.di.searchModule
import org.pierre.tvmaze.feature.theme_selection.data.di.themeSelectionDataModule
import org.pierre.tvmaze.feature.theme_selection.di.themeSelectionPresentationModule
import org.pierre.tvmaze.feature.theme_selection.domain.di.themeSelectionDomainModule
import org.pierre.tvmaze.network.di.networkModule

fun commonKoinInitializer(
    appModule: Module,
    config: (KoinApplication.() -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            dataStoreProviderModule,
            mainModule,
            networkModule,
            searchModule,
            themeSelectionDataModule,
            themeSelectionDomainModule,
            themeSelectionPresentationModule,
        )
    }
}
