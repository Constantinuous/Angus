package de.constantinuous.dietrich.data

import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 14.01.2017.
 */
class DatabaseTest : FeatureSpec(){


    init {
        feature("Empty Feature") {
            scenario("Test statement insert"){
                val foo = Database()
                foo.insertWithStatement()
            }

            scenario("Test prepared statement insert"){
                val foo = Database()
                foo.insertWithPreparedStatement()
            }

            scenario("Test getting stored procedures"){
                val foo = Database()
                foo.getStoredProcedures()
            }
        }
    }
}