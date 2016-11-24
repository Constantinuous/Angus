package de.constantinuous.angus.extract

/**
 * Created by RichardG on 24.11.2016.
 */
enum class vbLexerState {
    COMMENT, STRING_START, STRING_END, STRING_CONTINUE, STRING_CONCAT, NORMAL
}