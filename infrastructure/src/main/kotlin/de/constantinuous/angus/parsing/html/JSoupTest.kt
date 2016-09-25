package de.constantinuous.tessara.jsoup

import com.natpryce.hamkrest.assertion.*
import com.natpryce.hamkrest.*

import org.jsoup.Jsoup
import org.junit.Test

/**
 * Created by RichardG on 06.06.2016.
 */
class JSoupTest {
    @Test fun shouldFindEmptyBody() {
        val html = "<html><head><title>First parse</title></head></html>"
        val doc = Jsoup.parse(html)
        assertThat(doc.body().toString(), equalTo("<body></body>"))
    }

    @Test fun shouldFindArticle() {
        val html = """
        <html>
        <head><title>First parse</title></head>
        <body>
        <article>Text</article>
        </body>
        </html>"""

        val doc = Jsoup.parse(html)
        val article = doc.select("article").first().toString()

        assertThat(article, equalTo("<article>\n Text\n</article>"))
    }
}