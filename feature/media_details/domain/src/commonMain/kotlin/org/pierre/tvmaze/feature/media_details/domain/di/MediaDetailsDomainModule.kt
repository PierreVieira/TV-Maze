package org.pierre.tvmaze.feature.media_details.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.media_details.domain.usecase.GetMediaDetails
import org.pierre.tvmaze.feature.media_details.domain.usecase.impl.GetMediaDetailsUseCase

val mediaDetailsDomainModule = module {
    factoryOf(::GetMediaDetailsUseCase).bind<GetMediaDetails>()
}
