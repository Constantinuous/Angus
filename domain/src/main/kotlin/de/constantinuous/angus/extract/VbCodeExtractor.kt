package de.constantinuous.angus.extract

import de.constantinuous.angus.parsing.HtmlParser

/**
 * Created by RichardG on 24.11.2016.
 */
class VbCodeExtractor (private val htmlParser: HtmlParser):StringListener{
    override fun onString(str: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val strings = emptyList<String>()

    fun load(html: String){

    }

    fun parse(html: String){
        val serverCode = htmlParser.extractServerBlocks(html)
        val vbLexer = VbLexer(this)

        for(serverBlock in serverCode){

        }
    }



}