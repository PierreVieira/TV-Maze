package org.pierre.tvmaze.feature.favorites.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteMediaEntityMapper
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteMediaEntityMapperImpl
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteMediaModelMapper
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteMediaModelMapperImpl
import org.pierre.tvmaze.feature.favorites.data.repository.FavoritesRepositoryImpl
import org.pierre.tvmaze.feature.favorites.domain.repository.FavoritesRepository

val favoritesDataModule = module {
    factoryOf(::FavoritesRepositoryImpl).bind<FavoritesRepository>()
    factoryOf(::FavoriteMediaEntityMapperImpl).bind<FavoriteMediaEntityMapper>()
    factoryOf(::FavoriteMediaModelMapperImpl).bind<FavoriteMediaModelMapper>()
}
