package org.pierre.tvmaze.feature.search.domain.model.error

enum class SearchErrorType {
    EMPTY_QUERY,
    NETWORK_ERROR,
    NOT_POSSIBLE_TO_PERFORM_FAVORITE_TOGGLE,
    UNKNOWN_ERROR,
}
