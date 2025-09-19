package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.feature.search.domain.model.error.SearchErrorType

fun interface GetErrorTypeFromThrowable {
    operator fun invoke(throwable: Throwable): SearchErrorType
}
