package org.pierre.tvmaze.material_you.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.material_you.domain.repository.MaterialYouRepository
import org.pierre.tvmaze.material_you.domain.usecase.GetIsDynamicColorsEnabledFlow

internal class GetIsDynamicColorsEnabledFlowUseCase(
    private val repository: MaterialYouRepository,
) : GetIsDynamicColorsEnabledFlow {
    override fun invoke(): Flow<Boolean> = repository.getIsDynamicColorsEnabledFlow()
}
