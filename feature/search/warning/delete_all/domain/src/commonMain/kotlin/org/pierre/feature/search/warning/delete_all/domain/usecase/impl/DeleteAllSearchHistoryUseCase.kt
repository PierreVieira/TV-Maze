package org.pierre.feature.search.warning.delete_all.domain.usecase.impl

import org.pierre.feature.search.warning.delete_all.domain.repository.DeleteAllSearchRepository
import org.pierre.feature.search.warning.delete_all.domain.usecase.DeleteAllSearchHistory

internal class DeleteAllSearchHistoryUseCase(
    private val repository: DeleteAllSearchRepository
): DeleteAllSearchHistory {
    override suspend fun invoke() {
        repository.deleteAll()
    }
}
