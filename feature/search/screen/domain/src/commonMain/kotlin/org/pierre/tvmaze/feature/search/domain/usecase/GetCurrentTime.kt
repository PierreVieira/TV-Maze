package org.pierre.tvmaze.feature.search.domain.usecase

interface GetCurrentTime {
    suspend operator fun invoke(): Long
}
