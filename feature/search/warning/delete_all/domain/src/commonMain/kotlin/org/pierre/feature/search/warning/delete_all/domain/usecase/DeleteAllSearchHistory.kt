package org.pierre.feature.search.warning.delete_all.domain.usecase

fun interface DeleteAllSearchHistory {
    suspend operator fun invoke()
}
