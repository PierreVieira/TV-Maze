package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition

fun interface SaveNewSearchBarPosition {
    suspend operator fun invoke(currentPosition: SearchBarPosition)
}
