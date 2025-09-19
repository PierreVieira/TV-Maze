package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.usecase.GetDurationDisappearMenuDuration
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class GetDurationDisappearMenuDurationUseCase: GetDurationDisappearMenuDuration {
    override fun invoke(): Duration = 150.milliseconds
}
