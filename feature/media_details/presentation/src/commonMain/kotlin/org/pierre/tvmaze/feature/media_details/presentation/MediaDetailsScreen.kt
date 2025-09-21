package org.pierre.tvmaze.feature.media_details.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pierre.tvmaze.ui.components.icon_button.ArrowBackIconButton
import org.pierre.tvmaze.ui.components.icon_button.FavoriteIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailsScreen(
    id: Long,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Details")
                },
                navigationIcon = {
                    ArrowBackIconButton(onClick = onBack)
                },
                actions = {
                    FavoriteIconButton(isFavorite = false, onClick = {})
                }
            )
        }
    ) { paddingValues ->
        Text(text = "Media details for #$id", modifier = Modifier.padding(paddingValues))
    }
}
