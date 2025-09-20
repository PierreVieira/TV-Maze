package org.pierre.tvmaze.feature.search.domain.usecase

fun interface SaveRecentSearch {
    suspend operator fun invoke(query: String)
}
