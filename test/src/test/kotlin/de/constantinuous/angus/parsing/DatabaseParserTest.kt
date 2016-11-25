package de.constantinuous.angus.parsing

import de.constaninuous.angus.di.impl.createAllBindings
import de.constantinuous.angus.di.Binder
import de.constantinuous.angus.di.DiContainer
import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 09.10.2016.
 */
class DatabaseParserTest : FeatureSpec(){

    lateinit var di : Binder

    override fun beforeEach() {
        createAllBindings()
        di = DiContainer.instance
    }

    init {
        feature("Working Dependency Injection") {
            scenario("should allow a subclass to be bound to the parent interface") {
                val databaseParser = di.resolveImplementation(DatabaseParser::class.java)

                //databaseParser.extractFoo("jdbc:jtds:sqlserver://localhost:1433;instance=Foo", "sa", "secret")
            }
        }
    }
}