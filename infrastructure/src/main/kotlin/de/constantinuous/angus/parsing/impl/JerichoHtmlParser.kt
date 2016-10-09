package de.constantinuous.angus.parsing.impl

import de.constantinuous.angus.parsing.HtmlParser
import de.constantinuous.angus.parsing.ServerBlock
import net.htmlparser.jericho.*
import java.util.*

/**
 * Created by RichardG on 25.09.2016.
 */
class JerichoHtmlParser : HtmlParser {

    private fun isCommented(serverText: Element, comments: List<Element>): Boolean{
        var isCommented = false
        for(comment in comments){
            isCommented = comment.encloses(serverText)
            if(isCommented){ break }
        }
        return isCommented
    }

    override fun extractServerBlocks(htmlText: String): List<ServerBlock> {
        val serverBlocks = LinkedList<ServerBlock>()

        val source = Source(htmlText)
        val serverText = source.getAllElements(StartTagType.SERVER_COMMON)
        val comments = source.getAllElements(StartTagType.COMMENT)

        for(text in serverText){
            val content = text.toString().replace("<%", "").replace("%>", "")
            val isCommented = isCommented(text, comments)
            val row = text.rowColumnVector.row
            val column = text.rowColumnVector.column

            val serverBlock = ServerBlock(content, isCommented, row, column)
            serverBlocks.add(serverBlock)
        }

        return serverBlocks
    }

    override fun findAttributes(htmlText: String){
        val source = Source(htmlText)
        val attributes = source.parseAttributes()
        val uriAttributes = source.uriAttributes
        val allTags = source.allStartTags
        val depth2 = allTags[2].element.depth
        val depth3 = allTags[3].element.depth
        val foo = ""
    }

    private fun extractLinks(htmlText: String){
        val source = Source(htmlText)
        val allTags = source.allStartTags

        for(tag in allTags){
            if(tag.startTagType == StartTagType.COMMENT){
                continue
            }
        }
    }
}