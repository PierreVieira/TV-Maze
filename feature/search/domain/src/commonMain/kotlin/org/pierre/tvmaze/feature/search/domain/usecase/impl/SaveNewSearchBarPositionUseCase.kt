package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.SaveNewSearchBarPosition

internal class SaveNewSearchBarPositionUseCase(
    private val repository: SearchRepository,
): SaveNewSearchBarPosition {
    override suspend fun invoke(currentPosition: SearchBarPosition) {
        repository.saveNewSearchBarPosition(currentPosition)
    }
}
