package de.constantinuous.angus.parsing.sql

import de.constaninuous.angus.di.impl.createAllBindings
import de.constantinuous.angus.di.Binder
import de.constantinuous.angus.di.DiContainer
import de.constantinuous.angus.parsing.DatabaseParser
import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 25.11.2016.
 */
class TsqlParserTest : FeatureSpec(){

    lateinit var di : Binder

    override fun beforeEach() {
        createAllBindings()
        di = DiContainer.instance
    }

    init {
        feature("Working Dependency Injection") {
            scenario("should allow a subclass to be bound to the parent interface") {
                val tsqlParser = di.resolveImplementation(TSqlParser::class.java)

                tsqlParser.printDrink("bla")
            }
        }
    }
}