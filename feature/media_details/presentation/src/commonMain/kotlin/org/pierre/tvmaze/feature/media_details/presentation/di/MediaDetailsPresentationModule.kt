package org.pierre.tvmaze.feature.media_details.presentation.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsViewModel
import org.pierre.tvmaze.feature.media_details.presentation.factory.LoadingMediaItemsModelFactory
import org.pierre.tvmaze.feature.media_details.presentation.factory.impl.LoadingMediaItemsModelFactoryImpl

val mediaDetailsPresentationModule = module {
    viewModelOf(::MediaDetailsViewModel)
    factoryOf(::LoadingMediaItemsModelFactoryImpl).bind<LoadingMediaItemsModelFactory>()
}
