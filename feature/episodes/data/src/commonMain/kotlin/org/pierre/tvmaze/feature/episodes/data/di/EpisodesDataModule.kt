package org.pierre.tvmaze.feature.episodes.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedEntityMapper
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedModelMapper
import org.pierre.tvmaze.feature.episodes.data.mapper.impl.EpisodeWatchedEntityMapperImpl
import org.pierre.tvmaze.feature.episodes.data.mapper.impl.EpisodeWatchedModelMapperImpl
import org.pierre.tvmaze.feature.episodes.data.repository.EpisodesRepositoryImpl
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository

val episodesDataModule: Module = module {
    factoryOf(::EpisodesRepositoryImpl).bind<EpisodesRepository>()
    factoryOf(::EpisodeWatchedModelMapperImpl).bind<EpisodeWatchedModelMapper>()
    factoryOf(::EpisodeWatchedEntityMapperImpl).bind<EpisodeWatchedEntityMapper>()
}
