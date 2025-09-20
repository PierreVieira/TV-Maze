package org.pierre.tvmaze.feature.search.domain.usecase

interface EnforceSearchHistoryMaxSize {
    suspend operator fun invoke()
}
