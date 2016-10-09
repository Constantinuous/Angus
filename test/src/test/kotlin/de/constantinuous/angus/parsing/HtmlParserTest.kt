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

    val linkingAttributesHtml = """
    <html>
    <body>
    <form action="http://form.submit.google.com">
        <input type="submit" value="Go to Google" />
    </form>
    <a href="http://a.href.google.com" class="btn btn-default">Go to Google</a>
    <!--
        <a href="http://comment.a.href.google.com" class="btn btn-default">Go to Google</a>
        <input type="button" onclick="location.href='http://comment.input.onclick.google.com';" value="Go to Google" />
    -->
    <input type="button" onclick="location.href='http://input.onclick.google.com';" value="Go to Google" />
    <button onclick="location.href='http://www.button.onclick.goggle.com'">Go to Google</button>
    <form>
        <button formaction="http://button.formaction.google.com">Go to Google!</button>
    </form>
    <% Response.Redirect("http://redirect.google.com") %>
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



        feature("Finding Attributes") {

            scenario("Single Line Code Block Should have correct row and column") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                htmlParser.findAttributes(linkingAttributesHtml)

            }
        }
    }
}