package org.pierre.tvmaze.ui.components.picture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import org.jetbrains.compose.resources.painterResource
import org.pierre.tvmaze.components.shimmer.ShimmerComponent
import org.pierre.tvmaze.ui.theme.isAppInDarkTheme
import tvmaze.ui.components.picture.generated.resources.Res
import tvmaze.ui.components.picture.generated.resources.img_fallback_dark
import tvmaze.ui.components.picture.generated.resources.img_fallback_light

@Composable
fun PictureCommon(
    url: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    error: Painter? = null,
    placeholder: Painter? = null,
    onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
    contentDescription: String?,
) {

    var isLoading by remember { mutableStateOf(false) }

    if (isLoading) {
        ShimmerComponent(modifier = modifier)
    }

    AsyncImage(
        modifier = modifier,
        model = url?.takeIf { it.isNotBlank() } ?: getDefaultPlaceholder(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        error = error ?: getDefaultPlaceholder(),
        placeholder = placeholder ?: getDefaultPlaceholder(),
        onLoading = {
            isLoading = true
        },
        onError = { state ->
            isLoading = false
            onError?.invoke(state)
        },
        onSuccess = {
            isLoading = false
        }
    )
}

@Composable
private fun getDefaultPlaceholder(): Painter = painterResource(
    if (isAppInDarkTheme()) {
        Res.drawable.img_fallback_dark
    } else {
        Res.drawable.img_fallback_light
    }
)
