package de.constantinuous.angus.parsing

import de.constaninuous.angus.di.impl.PicoBinder
import de.constaninuous.angus.di.impl.createAllBindings
import de.constantinuous.angus.di.DiContainer
import de.constantinuous.angus.di.toImplementation
import de.constantinuous.angus.fs.FileSystem
import de.constantinuous.angus.fs.LocalFileSystem
import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 25.09.2016.
 */
class JerichoHtmlParserTest : FeatureSpec(){

    override fun beforeEach() {
        createAllBindings()
    }

    init {
        val di = DiContainer.instance

        feature("JerichoHtmlParserTest") {
            scenario("should allow a subclass to be bound to the parent interface") {
                val htmlParser = di.resolveImplementation(HtmlParser::class.java)
            }
        }
    }
}