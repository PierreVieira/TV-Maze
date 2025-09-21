package org.pierre.tvmaze.ui.components.icon_button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.ui.theme.isAppInDarkTheme
import tvmaze.ui.components.icon_button.generated.resources.Res
import tvmaze.ui.components.icon_button.generated.resources.add_to_favorites

private val LightFavoriteRed = Color(0xFFE57373)
private val DarkFavoriteRed = Color(0xFFEF9A9A)

private val favoriteRed: Color
    @Composable get() = if (isAppInDarkTheme()) DarkFavoriteRed else LightFavoriteRed

@Composable
fun FavoriteIconButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onClick: () -> Unit,
) {
    CommonIconButton(
        modifier = modifier,
        imageVector = if (isFavorite) {
            Icons.Filled.Favorite
        } else {
            Icons.Outlined.FavoriteBorder
        },
        contentDescription = stringResource(Res.string.add_to_favorites),
        onClick = onClick,
        tint = if (isFavorite) {
            favoriteRed
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
    )
}
