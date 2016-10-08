package de.constantinuous.angus.parsing.impl

import de.constantinuous.angus.parsing.HtmlParser
import de.constantinuous.angus.parsing.VbBlock
import net.htmlparser.jericho.*
import java.util.*

/**
 * Created by RichardG on 25.09.2016.
 */
class JerichoHtmlParser : HtmlParser {

    private fun test(){
        val htmlText = "<html></html>"
        val source = Source(htmlText)

    }

    override fun getVbScript(): List<VbBlock> {
        val htmlText = """<html>
            <% vbCode here %>
            <!-- <% Commented vbCode here %>-->
            <body>
            <% more vbCode %>
            </body>
            </html>"""
        val source = Source(htmlText)
        source.fullSequentialParse()
        val text = source.getTextExtractor().toString()
        val lala = source.getAllElements(StartTagType.SERVER_COMMON)
        val column0 = lala[0].rowColumnVector.column
        val row = lala[1].rowColumnVector.row
        val column1 = lala[1].rowColumnVector.column
        val pos = lala[1].rowColumnVector.pos
        val column2 = lala[2].rowColumnVector.column

        val it = source.getNodeIterator()
        val results = LinkedList<Segment>()
        while (it.hasNext()) {
            val cur = it.next()
            println(cur)
        }

        return LinkedList<VbBlock>()
    }
}