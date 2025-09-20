package org.pierre.tvmaze.feature.search.domain.usecase

interface DeleteAllSearchHistory {
    suspend operator fun invoke()
}
