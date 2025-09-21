package org.pierre.tvmaze.feature.episodes.presentation.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.episodes.presentation.factory.LoadingSeasonsFactory
import org.pierre.tvmaze.feature.episodes.presentation.factory.impl.LoadingSeasonsFactoryImpl
import org.pierre.tvmaze.feature.episodes.presentation.viewmodel.SeasonsViewModel

val episodesPresentationModule: Module = module {
    viewModelOf(::SeasonsViewModel)
    factoryOf(::LoadingSeasonsFactoryImpl).bind<LoadingSeasonsFactory>()
}
