package org.pierre.ui.components.stars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.pierre.core.model.theme.Theme
import org.pierre.tvmaze.model.common.media.StarsModel
import org.pierre.tvmaze.ui.theme.preview.PreviewTheme

@Composable
fun StarsComponent(
    starsModel: StarsModel,
    starSize: Dp = 16.dp,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
) {
    Row(modifier = modifier) {
        repeat(starsModel.fullStarsAmount) {
            StarComponent(
                imageVector = Icons.Filled.StarRate,
                tint = tint,
                starSize = starSize
            )
        }
        if (starsModel.showInAHalf) {
            StarComponent(
                imageVector = Icons.AutoMirrored.Filled.StarHalf,
                tint = tint,
                starSize = starSize
            )
        }
        val emptyStars = 5 - starsModel.fullStarsAmount - if (starsModel.showInAHalf) 1 else 0
        repeat(emptyStars) {
            StarComponent(
                imageVector = Icons.Outlined.StarRate,
                tint = tint,
                starSize = starSize,
            )
        }
    }
}

@Composable
private fun StarComponent(
    starSize: Dp,
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    tint: Color,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(starSize),
    )
}

@Preview
@Composable
private fun StarsComponentPreview(
    @PreviewParameter(StarsComponentParameterProvider::class)
    param: StarsComponentParameter,
) {
    PreviewTheme(param.theme) {
        StarsComponent(starsModel = param.starsModel)
    }
}

private class StarsComponentParameterProvider :
    PreviewParameterProvider<StarsComponentParameter> {
    override val values: Sequence<StarsComponentParameter> = sequenceOf(
        StarsComponentParameter(
            starsModel = StarsModel(
                fullStarsAmount = 5,
                showInAHalf = false,
            ),
            theme = Theme.LIGHT,
        ),
        StarsComponentParameter(
            starsModel = StarsModel(
                fullStarsAmount = 3,
                showInAHalf = true,
            ),
            theme = Theme.LIGHT,
        ),
        StarsComponentParameter(
            starsModel = StarsModel(
                fullStarsAmount = 0,
                showInAHalf = false,
            ),
            theme = Theme.LIGHT,
        ),

        StarsComponentParameter(
            starsModel = StarsModel(
                fullStarsAmount = 5,
                showInAHalf = false,
            ),
            theme = Theme.DARK,
        ),

        StarsComponentParameter(
            starsModel = StarsModel(
                fullStarsAmount = 3,
                showInAHalf = true,
            ),
            theme = Theme.DARK,
        ),
        StarsComponentParameter(
            starsModel = StarsModel(
                fullStarsAmount = 0,
                showInAHalf = false,
            ),
            theme = Theme.DARK,
        ),
    )
}

private data class StarsComponentParameter(
    val starsModel: StarsModel,
    val theme: Theme,
)
