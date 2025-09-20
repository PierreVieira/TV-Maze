package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.model.error.SearchErrorType
import org.pierre.tvmaze.feature.search.domain.model.exception.EmptySearchQueryException
import org.pierre.tvmaze.feature.search.domain.usecase.GetErrorTypeFromThrowable

internal class GetErrorTypeFromThrowableUseCase: GetErrorTypeFromThrowable {
    override fun invoke(throwable: Throwable): SearchErrorType = when {
        throwable is EmptySearchQueryException -> SearchErrorType.EMPTY_QUERY
        else -> SearchErrorType.UNKNOWN_ERROR
    }
}
