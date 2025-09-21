package org.pierre.tvmaze.feature.episodes.domain.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetEpisodes
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetWatchedEpisodesFlow
import org.pierre.tvmaze.feature.episodes.domain.usecase.impl.GetEpisodesUseCase
import org.pierre.tvmaze.feature.episodes.domain.usecase.impl.GetWatchedEpisodesFlowUseCase

val episodesDomainModule: Module = module {
    factoryOf(::GetEpisodesUseCase).bind<GetEpisodes>()
    factoryOf(::GetWatchedEpisodesFlowUseCase).bind<GetWatchedEpisodesFlow>()
}
