package org.pierre.tvmaze.feature.episodes.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.ui.components.icon_button.CommonIconButton

@Composable
internal fun SeasonHeader(
    number: DataStatus<Int>,
    isCollapsed: Boolean,
    onToggleSeason: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable(enabled = number is DataStatus.Loaded) {
            (number as? DataStatus.Loaded)?.data?.let(onToggleSeason)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        val isLoaded = number is DataStatus.Loaded
        CommonIconButton(
            imageVector = if (isCollapsed) {
                Icons.AutoMirrored.Filled.KeyboardArrowRight
            } else {
                Icons.Filled.KeyboardArrowDown
            },
            contentDescription = if (isCollapsed) "Expand season" else "Collapse season",
            onClick = {
                if (isLoaded) {
                    number.data.let(onToggleSeason)
                }
            }
        )
        SeasonNumberText(number)
    }
}
