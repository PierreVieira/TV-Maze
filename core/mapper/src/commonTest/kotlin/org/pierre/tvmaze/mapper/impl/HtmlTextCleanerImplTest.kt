package org.pierre.tvmaze.mapper.impl

import kotlin.test.Test
import kotlin.test.assertEquals

class HtmlTextCleanerImplTest {

    private val cleaner = HtmlTextCleanerImpl()

    @Test
    fun `given html with tags when clean then returns plain text without tags`() {
        // Given
        val html = "<p>Hello <b>World</b>!</p>\n<div>Line 2</div>"

        // When
        val result = cleaner.clean(html)

        // Then
        // Our cleaner replaces tags with spaces, which can leave a space before punctuation.
        assertEquals("Hello World ! Line 2", result)
    }

    @Test
    fun `given html with entities when clean then decodes common entities`() {
        // Given
        val html = "Rock &amp; Roll &lt;3 &#39;quote&#39; &quot;double&quot; &nbsp;end"

        // When
        val result = cleaner.clean(html)

        // Then
        assertEquals("Rock & Roll <3 'quote' \"double\" end", result)
    }

    @Test
    fun `given html with multiple spaces when clean then collapses spaces and trims`() {
        // Given
        val html = "<div>  A   lot    of     spaces   </div>"

        // When
        val result = cleaner.clean(html)

        // Then
        assertEquals("A lot of spaces", result)
    }
}
