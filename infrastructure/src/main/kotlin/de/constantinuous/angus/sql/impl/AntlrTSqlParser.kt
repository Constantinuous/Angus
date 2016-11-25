package de.constantinuous.angus.sql.impl

import de.constantinuous.angus.parsing.sql.TSqlParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.tool.Rule

import tsqlLexer
import tsqlParser
/**
 * Created by RichardG on 25.11.2016.
 */
class AntlrTSqlParser : TSqlParser {

    private fun getContext(sqlCode: String): RuleContext{
        // Get our lexer
        val lexer = tsqlLexer(ANTLRInputStream(sqlCode))

        // Get a list of matched tokens
        val tokens = CommonTokenStream(lexer)

        // Pass the tokens to the parser
        val parser = tsqlParser(tokens)

        // Specify our entry point
        val drinkSentenceContext = parser.tsql_file()

        return drinkSentenceContext
    }

    override fun printDrink(drinkSentence: String) {
        val drinkSentenceContext = getContext(drinkSentence)
        // Walk it and attach our listener
        val walker = ParseTreeWalker()
        val listener = AntlrTsqlListener()
        walker.walk(listener, drinkSentenceContext)
    }

    override fun printTree(drinkSentence: String){
        val drinkSentenceContext = getContext(drinkSentence)
        explore(drinkSentenceContext, 0)
    }

    private fun explore(ctx: RuleContext, indentation: Int){
        val ruleName = tsqlParser.ruleNames[ctx.ruleIndex]
        for(i in 0..indentation){
            print("  ")
        }
        println(ruleName)

        for(i in 0..ctx.childCount){
            val element = ctx.getChild(i)
            if(element is RuleContext){
                explore(element, indentation + 1)
            }
        }
    }

}