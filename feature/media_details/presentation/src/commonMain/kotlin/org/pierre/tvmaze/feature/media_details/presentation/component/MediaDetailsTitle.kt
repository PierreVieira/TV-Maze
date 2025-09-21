package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.data_status.DataStatus

@Composable
internal fun MediaDetailsTitle(
    name: DataStatus<String>,
    modifier: Modifier = Modifier,
) {
    name.ToContent(
        modifier = modifier
            .fillMaxWidth(0.75f)
            .height(24.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(6.dp)),
    ) { title ->
        Text(
            modifier = modifier,
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
    }
}
