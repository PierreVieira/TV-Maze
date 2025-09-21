package org.pierre.tvmaze.feature.favorites.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteShowEntityMapper
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteShowEntityMapperImpl
import org.pierre.tvmaze.feature.favorites.data.repository.FavoritesRepositoryImpl
import org.pierre.tvmaze.feature.favorites.domain.repository.FavoritesRepository

val favoritesDataModule = module {
    factoryOf(::FavoritesRepositoryImpl).bind<FavoritesRepository>()
    factoryOf(::FavoriteShowEntityMapperImpl).bind<FavoriteShowEntityMapper>()
}
