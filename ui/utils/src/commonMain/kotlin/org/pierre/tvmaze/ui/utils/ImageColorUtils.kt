package org.pierre.tvmaze.ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import kotlin.math.max

fun dominantColorOf(image: ImageBitmap): Color {
    val map = image.toPixelMap()

    // Sample grid (keeps it fast on big images)
    val stepX = max(1, map.width / 64)
    val stepY = max(1, map.height / 64)

    // 3-3-3 bit buckets (8 levels per channel → 512 buckets)
    val counts = IntArray(512)
    var bestKey = 0
    var bestCount = -1

    fun bucketize(color: Color): Int {
        val red = ((color.red * 255f).toInt() shr 5) and 0x7
        val green = ((color.green * 255f).toInt() shr 5) and 0x7
        val blue = ((color.blue * 255f).toInt() shr 5) and 0x7
        return (red shl 6) or (green shl 3) or blue
    }

    for (y in 0 until map.height step stepY) {
        for (x in 0 until map.width step stepX) {
            val c = map[x, y]
            if (c.alpha < 0.5f) continue // ignore mostly transparent pixels
            val key = bucketize(c)
            val n = ++counts[key]
            if (n > bestCount) {
                bestCount = n
                bestKey = key
            }
        }
    }

    // Convert bucket center back to Color
    val r = ((bestKey shr 6) and 0x7) * 255 / 7
    val g = ((bestKey shr 3) and 0x7) * 255 / 7
    val b = (bestKey and 0x7) * 255 / 7
    return Color(r / 255f, g / 255f, b / 255f, 1f)
}

/** Blend this color over [base] so the result is toned down and always readable. */
fun Color.blendOver(base: Color, alpha: Float = 0.35f): Color =
    Color(
        red = base.red * (1f - alpha) + red * alpha,
        green = base.green * (1f - alpha) + green * alpha,
        blue = base.blue * (1f - alpha) + blue * alpha,
        alpha = 1f
    )
