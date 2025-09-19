package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.domain.usecase.GetNewSearchBarPositionDueToToggle

internal class GetNewSearchBarPositionDueToToggleUseCase : GetNewSearchBarPositionDueToToggle {
    override fun invoke(currentPosition: SearchBarPosition): SearchBarPosition =
        when (currentPosition) {
            SearchBarPosition.TOP -> SearchBarPosition.BOTTOM
            SearchBarPosition.BOTTOM -> SearchBarPosition.TOP
        }
}
