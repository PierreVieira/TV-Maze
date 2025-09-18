package org.pierre.tvmaze.core.koin_initializer.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.pierre.tvmaze.core.data_store_provider.di.dataStoreProviderModule
import org.pierre.tvmaze.feature.main.di.mainModule
import org.pierre.tvmaze.feature.theme_selection.di.themeSelectionModule
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
            themeSelectionModule,
        )
    }
}
