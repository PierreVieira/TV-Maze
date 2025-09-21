package org.pierre.tvmaze.feature.favorites.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.feature.favorites.domain.usecase.impl.ToggleFavoriteUseCase

val favoritesDomainModule = module {
    factoryOf(::ToggleFavoriteUseCase).bind<ToggleFavorite>()
}
