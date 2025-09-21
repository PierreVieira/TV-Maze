package org.pierre.tvmaze.feature.media_details.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsViewModel

val mediaDetailsPresentationModule = module {
    viewModelOf(::MediaDetailsViewModel)
}
