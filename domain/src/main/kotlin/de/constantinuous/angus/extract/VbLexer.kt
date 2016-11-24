package de.constantinuous.angus.extract

import de.constantinuous.angus.parsing.CodePiece

/**
 * Created by RichardG on 24.11.2016.
 */
class VbLexer(private val stringListener: StringListener) {

    private var state = vbLexerState.NORMAL

    private var currentString = ""

    fun parsePiece(codePiece: CodePiece){
        for(character in codePiece.content){
            parseChar(character)
        }
        finishLine()
    }

    private fun parseChar(character : Char){
        if(state == vbLexerState.COMMENT){
            return
        }

        when(character){
            '"' -> parseParanthesis()
            '\''  -> parseComment()
            '_'  -> parseUnderScore()
            '=' -> parseAnd()
            '\r' -> parseCarriageReturn()
            '\n' -> parseLineFeed()
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
            else -> vbLexerState.COMMENT
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
            else -> vbLexerState.NORMAL
        }
        println("Line Feed with state $state")
        finishLine()
    }

    private fun finishLine(){
        if(state == vbLexerState.STRING_CONTINUE || state == vbLexerState.COMMENT){
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