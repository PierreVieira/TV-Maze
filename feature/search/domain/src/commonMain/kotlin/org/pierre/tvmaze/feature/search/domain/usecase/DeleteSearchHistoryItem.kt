package org.pierre.tvmaze.feature.search.domain.usecase

interface DeleteSearchHistoryItem {
    suspend operator fun invoke(id: Long)
}
