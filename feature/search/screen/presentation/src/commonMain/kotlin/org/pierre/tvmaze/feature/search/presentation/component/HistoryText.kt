package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle

@Composable
fun HistoryText(
    query: String,
    range: IntRange,
    modifier: Modifier = Modifier,
) {
    val textStyle = MaterialTheme.typography.bodyLarge
    val highlightColor = MaterialTheme.colorScheme.primary
    val highlightStyle = textStyle.copy(color = highlightColor)

    Text(
        modifier = modifier,
        text = remember(query, range, textStyle, highlightColor) {
            val start = range.first
            val endInclusive = range.last
            val isValid =
                start >= 0 &&
                        endInclusive >= start &&
                        endInclusive < query.length

            buildAnnotatedString {
                if (!isValid) {
                    append(query)
                } else {
                    val endExclusive = endInclusive + 1
                    append(query.substring(0, start))
                    withStyle(
                        style = SpanStyle(
                            color = highlightStyle.color,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append(query.substring(start, endExclusive))
                    }
                    append(query.substring(endExclusive))
                }
            }
        },
        style = textStyle,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}
