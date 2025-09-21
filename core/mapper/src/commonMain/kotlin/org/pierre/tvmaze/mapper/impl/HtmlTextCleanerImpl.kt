package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.mapper.HtmlTextCleaner

internal class HtmlTextCleanerImpl : HtmlTextCleaner {
    private val tagRegex = Regex("<[^>]+>")
    private val multiSpaceRegex = Regex("\\s{2,}")

    override fun clean(html: String): String {
        // Remove tags
        var text = tagRegex.replace(html, " ")
        // Replace some common entities
        text = text
            .replace("&nbsp;", " ")
            .replace("&amp;", "&")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&#39;", "'")
            .replace("&quot;", "\"")
        // Collapse multiple spaces/newlines and trim
        text = multiSpaceRegex.replace(text, " ")
        return text.trim()
    }
}
