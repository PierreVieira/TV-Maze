package org.pierre.tvmaze.feature.episodes.domain.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.episodes.domain.usecase.EpisodesToSeason
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetEpisodesBySeason
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetWatchedEpisodesBySeasonFlow
import org.pierre.tvmaze.feature.episodes.domain.usecase.ToggleEpisodeWatched
import org.pierre.tvmaze.feature.episodes.domain.usecase.impl.EpisodesToSeasonsUseCase
import org.pierre.tvmaze.feature.episodes.domain.usecase.impl.GetEpisodesBySeasonUseCase
import org.pierre.tvmaze.feature.episodes.domain.usecase.impl.GetWatchedEpisodesBySeasonFlowUseCase
import org.pierre.tvmaze.feature.episodes.domain.usecase.impl.ToggleEpisodeWatchedUseCase

val episodesDomainModule: Module = module {
    factoryOf(::GetEpisodesBySeasonUseCase).bind<GetEpisodesBySeason>()
    factoryOf(::GetWatchedEpisodesBySeasonFlowUseCase).bind<GetWatchedEpisodesBySeasonFlow>()
    factoryOf(::EpisodesToSeasonsUseCase).bind<EpisodesToSeason>()
    factoryOf(::ToggleEpisodeWatchedUseCase).bind<ToggleEpisodeWatched>()
}
