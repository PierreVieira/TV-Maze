package org.pierre.feature.search.warning.delete_item.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class DeleteSearchItemDialogRoute(
    val id: Long,
    val name: String,
)
