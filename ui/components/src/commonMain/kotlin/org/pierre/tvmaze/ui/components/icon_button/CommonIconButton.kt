package org.pierre.tvmaze.ui.components.icon_button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommonIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: StringResource,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = stringResource(contentDescription),
        )
    }
}
