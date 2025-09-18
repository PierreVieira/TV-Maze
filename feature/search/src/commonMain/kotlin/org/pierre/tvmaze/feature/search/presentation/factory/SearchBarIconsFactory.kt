package org.pierre.tvmaze.feature.search.presentation.factory

import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconsModel

interface SearchBarIconsFactory {
    fun create(isExpanded: Boolean, query: String): SearchBarIconsModel
}
