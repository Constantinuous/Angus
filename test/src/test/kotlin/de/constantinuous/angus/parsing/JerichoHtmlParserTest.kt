package de.constantinuous.angus.parsing

import de.constaninuous.angus.di.impl.PicoBinder
import de.constaninuous.angus.di.impl.createAllBindings
import de.constantinuous.angus.di.Binder
import de.constantinuous.angus.di.DiContainer
import de.constantinuous.angus.di.toImplementation
import de.constantinuous.angus.fs.FileSystem
import de.constantinuous.angus.fs.LocalFileSystem
import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 25.09.2016.
 */
class JerichoHtmlParserTest : FeatureSpec(){

    lateinit var di : Binder

    override fun beforeEach() {
        createAllBindings()
        di = DiContainer.instance
    }

    init {
        feature("JerichoHtmlParserTest") {
            scenario("should allow a subclass to be bound to the parent interface") {
                di.resolveImplementation(HtmlParser::class.java)
            }

            scenario("Should find all text") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)

                val vbScript = htmlParser.getVbScript()
            }
        }
    }
}