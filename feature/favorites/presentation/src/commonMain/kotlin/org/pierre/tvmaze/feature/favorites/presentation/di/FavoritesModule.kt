package org.pierre.tvmaze.feature.favorites.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.feature.favorites.presentation.viewmodel.FavoritesViewModel

val favoritesPresentationModule = module {
    viewModelOf(::FavoritesViewModel)
}
