package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.usecase.GetCurrentTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
internal class GetCurrentTimeUseCase: GetCurrentTime {

    private val system = Clock.System

    override suspend fun invoke(): Long = system.now().epochSeconds
}
