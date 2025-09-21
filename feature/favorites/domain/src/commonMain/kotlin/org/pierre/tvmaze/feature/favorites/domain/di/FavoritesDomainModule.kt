package org.pierre.tvmaze.feature.favorites.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavorites
import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavoritesFlow
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.feature.favorites.domain.usecase.impl.GetFavoritesFlowUseCase
import org.pierre.tvmaze.feature.favorites.domain.usecase.impl.GetFavoritesUseCase
import org.pierre.tvmaze.feature.favorites.domain.usecase.impl.ToggleFavoriteUseCase

val favoritesDomainModule = module {
    factoryOf(::ToggleFavoriteUseCase).bind<ToggleFavorite>()
    factoryOf(::GetFavoritesFlowUseCase).bind<GetFavoritesFlow>()
    factoryOf(::GetFavoritesUseCase).bind<GetFavorites>()
}
