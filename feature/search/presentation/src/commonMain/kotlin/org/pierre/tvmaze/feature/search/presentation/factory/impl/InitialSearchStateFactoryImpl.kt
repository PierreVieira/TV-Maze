package org.pierre.tvmaze.feature.search.presentation.factory.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchState

internal class InitialSearchStateFactoryImpl(
    private val searchBarIconsFactory: SearchBarIconsFactory
): InitialSearchStateFactory {

    override fun create(): SearchState = SearchState(
        query = INITIAL_QUERY,
        isShowingMenu = INITIAL_IS_SHOWING_MENU,
        searchItems = emptyList(),
        iconsModel = searchBarIconsFactory.create(
            query = INITIAL_QUERY,
        ),
        searchBarPosition = SearchBarPosition.TOP,
    )

    companion object {
        private const val INITIAL_IS_SHOWING_MENU = false
        private const val INITIAL_QUERY = ""
    }
}
