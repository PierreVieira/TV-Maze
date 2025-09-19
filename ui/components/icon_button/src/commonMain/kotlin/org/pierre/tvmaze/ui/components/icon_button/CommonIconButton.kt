package org.pierre.tvmaze.ui.components.icon_button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * A reusable composable wrapper around [IconButton] and [Icon] that standardizes
 * the appearance and accessibility of icon-only buttons across the app.
 *
 * This function ensures that all icon buttons provide a localized
 * [contentDescription] for accessibility, using a [StringResource].
 *
 * Example usage:
 * ```
 * CommonIconButton(
 *     imageVector = Icons.Default.Close,
 *     contentDescription = Res.string.close_button_description,
 *     onClick = { /* Handle close action */ },
 * )
 * ```
 *
 * @param modifier The [Modifier] to be applied to the [IconButton]. Defaults to [Modifier].
 * @param imageVector The [ImageVector] that defines the icon to be displayed inside the button.
 * @param contentDescription The [StringResource] providing a localized description of the icon,
 * used for accessibility (screen readers).
 * @param onClick The callback invoked when the button is clicked.
 */
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
