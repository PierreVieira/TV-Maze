package org.pierre.tvmaze.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.pierre.tvmaze.core.data_store_provider.di.dataStoreProviderModule
import org.pierre.tvmaze.feature.main.di.mainModule
import org.pierre.tvmaze.feature.theme_selection.di.themeSelectionModule

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            dataStoreProviderModule,
            mainModule,
            themeSelectionModule,
        )
    }
}
