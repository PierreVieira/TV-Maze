package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.domain.repository.SearchBarRepository
import org.pierre.tvmaze.feature.search.domain.usecase.SaveNewSearchBarPosition

internal class SaveNewSearchBarPositionUseCase(
    private val repository: SearchBarRepository,
): SaveNewSearchBarPosition {
    override suspend fun invoke(currentPosition: SearchBarPosition) {
        repository.saveNewSearchBarPosition(currentPosition)
    }
}
