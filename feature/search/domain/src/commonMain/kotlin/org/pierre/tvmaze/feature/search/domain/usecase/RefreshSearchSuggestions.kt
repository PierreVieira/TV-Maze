package org.pierre.tvmaze.feature.search.domain.usecase

interface RefreshSearchSuggestions {
    suspend operator fun invoke()
}
