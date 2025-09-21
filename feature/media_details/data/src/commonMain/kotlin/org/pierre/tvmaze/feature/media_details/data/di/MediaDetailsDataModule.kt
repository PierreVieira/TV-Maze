package org.pierre.tvmaze.feature.media_details.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.media_details.data.repository.MediaItemRepositoryImpl
import org.pierre.tvmaze.feature.media_details.domain.repository.MediaItemRepository

val mediaDetailsDataModule = module {
    factoryOf(::MediaItemRepositoryImpl).bind<MediaItemRepository>()
}
