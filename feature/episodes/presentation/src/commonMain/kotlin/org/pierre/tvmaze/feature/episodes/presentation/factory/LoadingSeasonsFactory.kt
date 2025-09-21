package org.pierre.tvmaze.feature.episodes.presentation.factory

import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel

fun interface LoadingSeasonsFactory {
    fun create(): List<SeasonModel>
}
