package de.constantinuous.angus.parsing

/**
 * Created by RichardG on 25.09.2016.
 */
interface HtmlParser {

    fun extractServerBlocks(htmlText: String): List<ServerBlock>

}