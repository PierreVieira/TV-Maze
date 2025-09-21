package org.pierre.tvmaze.mapper

/**
 * Mapper responsible for cleaning HTML tags from a text coming from the API.
 */
fun interface HtmlTextCleaner {
    fun clean(html: String): String
}
