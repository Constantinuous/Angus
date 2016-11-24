package de.constantinuous.angus.extract

import de.constantinuous.angus.parsing.CodePiece

/**
 * Created by RichardG on 24.11.2016.
 */
class VbLexer(private val stringListener: StringListener) {

    private var state = vbLexerState.NORMAL
    private var accumulatedLine = ""

    private var currentString = ""

    fun parsePiece(codePiece: CodePiece){
        for(character in codePiece.content){
            parseChar(character)
        }
        finishLine()
    }

    private fun parseChar(character : Char){
        accumulatedLine += character
        when(character){
            '\r' -> parseCarriageReturn()
            '\n' -> parseLineFeed()
        }

        if(state == vbLexerState.COMMENT_START){
            return
        }

        when(character){
            '"' -> parseParanthesis()
            '\''  -> parseComment()
            '_'  -> parseUnderScore()
            '&' -> parseAnd()
            else -> parseNormalChar(character)
        }
    }

    private fun parseParanthesis(){
        state = when(state){
            vbLexerState.STRING_START -> vbLexerState.STRING_END
            else -> vbLexerState.STRING_START
        }
    }

    private fun parseComment(){
        state = when(state){
            vbLexerState.STRING_START -> vbLexerState.STRING_START
            else -> vbLexerState.COMMENT_START
        }
    }

    private fun parseUnderScore(){
        state = when(state){
            vbLexerState.STRING_CONCAT -> vbLexerState.STRING_CONTINUE
            else -> state
        }
    }

    private fun parseAnd(){
        state = when(state){
            vbLexerState.STRING_END -> vbLexerState.STRING_CONCAT
            else -> state
        }
    }

    private fun parseCarriageReturn(){
        parseLineFeed()
    }

    private fun parseLineFeed(){
        state = when(state){
            vbLexerState.STRING_CONTINUE -> vbLexerState.STRING_CONTINUE
            vbLexerState.COMMENT_START -> vbLexerState.COMMENT_END
            else -> vbLexerState.NORMAL
        }
        finishLine()
    }

    private fun finishLine(){
        accumulatedLine = ""
        if(state == vbLexerState.STRING_CONTINUE || state == vbLexerState.COMMENT_END){
            return
        }
        state = vbLexerState.NORMAL
        if(currentString.length > 0){
            stringListener.onString(currentString)
        }
        currentString = ""
    }

    private fun parseNormalChar(character: Char){
        if(state == vbLexerState.STRING_START){
            currentString += character
        }
    }
}