package de.constantinuous.angus.parsing

import de.constaninuous.angus.di.impl.createAllBindings
import de.constantinuous.angus.di.Binder
import de.constantinuous.angus.di.DiContainer
import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 25.09.2016.
 */
class HtmlParserTest : FeatureSpec(){

    lateinit var di : Binder

    val threeBlockHtml = """
    <html>
    <% vbCode here %>
    <!-- <% Commented vbCode here %>-->
    <body>
     <% more vbCode
    with second line
    %>
    </body>
    </html>
    """.trimIndent()

    override fun beforeEach() {
        createAllBindings()
        di = DiContainer.instance
    }

    init {
        feature("Working Dependency Injection") {
            scenario("should allow a subclass to be bound to the parent interface") {
                di.resolveImplementation(HtmlParser::class.java)
            }
        }

        feature("Finding Code Block Content, even in comments") {

            scenario("Should find all three code blocks") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val serverCode = htmlParser.extractServerBlocks(threeBlockHtml)

                serverCode.size shouldEqual 3
            }

            scenario("Should find commented code block") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val serverCode = htmlParser.extractServerBlocks(threeBlockHtml)

                serverCode[0].isCommented shouldEqual false
                serverCode[1].isCommented shouldEqual true
                serverCode[2].isCommented shouldEqual false
            }

            scenario("Single Line Code Block Should have correct content") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val serverCode = htmlParser.extractServerBlocks(threeBlockHtml)

                serverCode[0].content shouldEqual " vbCode here "
            }

            scenario("Commented Code Block Should have correct content") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val serverCode = htmlParser.extractServerBlocks(threeBlockHtml)

                serverCode[1].content shouldEqual " Commented vbCode here "
            }

            scenario("Multi Line Code Block Should have correct content") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val serverCode = htmlParser.extractServerBlocks(threeBlockHtml)

                serverCode[2].content shouldEqual " more vbCode\nwith second line\n"
            }
        }

        feature("Finding Code Block Rows and Columns") {

            scenario("Single Line Code Block Should have correct row and column") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val serverCode = htmlParser.extractServerBlocks(threeBlockHtml)

                serverCode[0].row shouldEqual 2
                serverCode[0].column shouldEqual 1
            }

            scenario("Commented Code Block Should have correct row and column") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val serverCode = htmlParser.extractServerBlocks(threeBlockHtml)

                serverCode[1].row shouldEqual 3
                serverCode[1].column shouldEqual 6
            }

            scenario("Multi Line Code Block Should have correct row and column") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val serverCode = htmlParser.extractServerBlocks(threeBlockHtml)

                serverCode[2].row shouldEqual 5
                serverCode[2].column shouldEqual 2
            }
        }
    }
}