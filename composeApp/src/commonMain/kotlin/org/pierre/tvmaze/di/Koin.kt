package org.pierre.tvmaze.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.pierre.tvmaze.feature.main.di.mainModule
import org.pierre.tvmaze.feature.theme_selection.di.themeSelectionModule

expect val platformModule: Module

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            platformModule,
            mainModule,
            themeSelectionModule,
        )
    }
}
