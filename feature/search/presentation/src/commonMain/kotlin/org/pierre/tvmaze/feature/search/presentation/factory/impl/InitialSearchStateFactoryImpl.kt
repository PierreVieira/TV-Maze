package org.pierre.tvmaze.feature.search.presentation.factory.impl

import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchState

internal class InitialSearchStateFactoryImpl(
    private val searchBarIconsFactory: SearchBarIconsFactory
): InitialSearchStateFactory {

    override fun create(): SearchState = SearchState(
        query = INITIAL_QUERY,
        isExpanded = INITIAL_IS_EXPANDED,
        searchResults = emptyList(),
        iconsModel = searchBarIconsFactory.create(
            isExpanded = INITIAL_IS_EXPANDED,
            query = INITIAL_QUERY,
        )
    )

    companion object {
        private const val INITIAL_IS_EXPANDED = false
        private const val INITIAL_QUERY = ""
    }
}
