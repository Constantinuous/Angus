package de.constantinuous.angus.sql.impl

import de.constantinuous.angus.parsing.sql.TSqlParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

import tsqlLexer
import tsqlParser
/**
 * Created by RichardG on 25.11.2016.
 */
class AntlrTSqlParser : TSqlParser {

    override fun printDrink(drinkSentence: String) {
        // Get our lexer
        val lexer = tsqlLexer(ANTLRInputStream(drinkSentence))

        // Get a list of matched tokens
        val tokens = CommonTokenStream(lexer)

        // Pass the tokens to the parser
        val parser = tsqlParser(tokens)

        // Specify our entry point
        val drinkSentenceContext = parser.tsql_file()

        // Walk it and attach our listener
        val walker = ParseTreeWalker()
        val listener = AntlrTsqlListener()
        walker.walk(listener, drinkSentenceContext)
    }

}