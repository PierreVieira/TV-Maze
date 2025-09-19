package org.pierre.tvmaze.feature.search.presentation.factory

import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconsModel

fun interface SearchBarIconsFactory {
    fun create(isExpanded: Boolean, query: String): SearchBarIconsModel
}
