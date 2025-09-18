package org.pierre.tvmaze.di

import org.koin.core.KoinApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.core.koin_initializer.di.commonKoinInitializer
import org.pierre.tvmaze.presentation.AppViewModel

fun initializeKoin(config: (KoinApplication.() -> Unit)? = null) {
    commonKoinInitializer(
        appModule = module {
            viewModelOf(::AppViewModel)
        },
        config = config
    )
}
