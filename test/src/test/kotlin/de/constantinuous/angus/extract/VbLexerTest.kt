package de.constantinuous.angus.extract

import com.nhaarman.mockito_kotlin.*
import de.constaninuous.angus.di.impl.createAllBindings
import de.constantinuous.angus.di.Binder
import de.constantinuous.angus.di.DiContainer
import de.constantinuous.angus.parsing.CodePiece
import de.constantinuous.angus.parsing.DatabaseParser
import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 24.11.2016.
 */
class VbLexerTest : FeatureSpec(){

    lateinit var mockStringListener : StringListener
    lateinit var vbLexer : VbLexer

    override fun beforeEach() {
        mockStringListener = mock<StringListener>()
        vbLexer = VbLexer(mockStringListener)
    }

    init {
        feature("Lexer should extract vb strings") {
            scenario("should extract single line string") {
                val codePiece = CodePiece("aString = \"good result\"", 0, 0)

                vbLexer.parsePiece(codePiece)

                verify(mockStringListener).onString("good result")
            }

            scenario("should ignore comment") {
                val codePiece = CodePiece("'aString = \"good result\"", 0, 0)

                vbLexer.parsePiece(codePiece)

                verify(mockStringListener, never()).onString(any())
            }

            scenario("should extract second line string") {
                val codePiece = CodePiece("""
                    aString = "good result"
                    """", 0, 0)

                vbLexer.parsePiece(codePiece)

                verify(mockStringListener, times(1)).onString("good result")
            }

            scenario("should extract both strings") {
                val codePiece = CodePiece("""
                    aString = "good result"
                    aString = "better result"
                    """", 0, 0)

                vbLexer.parsePiece(codePiece)

                verify(mockStringListener, times(1)).onString("good result")
                verify(mockStringListener, times(1)).onString("better result")
            }

            scenario("should extract both strings even with data in between") {
                val codePiece = CodePiece("""
                    aString = "good result"
                    aValue = 5
                    aString = "better result"
                    """", 0, 0)

                vbLexer.parsePiece(codePiece)

                verify(mockStringListener, times(1)).onString("good result")
                verify(mockStringListener, times(1)).onString("better result")
            }

            scenario("should extract three-line string as one") {
                val codePiece = CodePiece("""
                    aString = "good" & _
                    ", better" & _
                    ", best result"
                    """", 0, 0)

                vbLexer.parsePiece(codePiece)

                verify(mockStringListener, times(1)).onString("good, better, best result")
            }

            scenario("should extract three-line string with comment in between as one") {
                val codePiece = CodePiece("""
                    aString = "good" & _
                    ' "a comment" & _
                    ", best result"
                    """", 0, 0)

                vbLexer.parsePiece(codePiece)

                verify(mockStringListener, times(1)).onString("good, best result")
            }

            scenario("should extract three-line string as one, even with comment at the end") {
                val codePiece = CodePiece("""
                    aString = "good" & _
                    ", better" & _
                    ", best result"
                    ' another cool comment """", 0, 0)

                vbLexer.parsePiece(codePiece)

                verify(mockStringListener, times(1)).onString("good, better, best result")
            }
        }
    }
}