package de.constantinuous.angus.extract

/**
 * Created by RichardG on 24.11.2016.
 */
enum class vbLexerState {
    COMMENT_START, COMMENT_END, STRING_START, STRING_END, STRING_CONTINUE, STRING_CONCAT, NORMAL
}