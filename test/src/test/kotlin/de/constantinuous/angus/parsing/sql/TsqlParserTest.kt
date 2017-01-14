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
        feature("Parse T-SQL Code") {
            scenario("should find stuff in select") {
                val tsqlParser = di.resolveImplementation(TSqlParser::class.java)

                tsqlParser.printDrink("SELECT Field1, Field2 FROM M_Data WHERE Field3 = Value")
            }

            scenario("should find stuff in delete") {
                val tsqlParser = di.resolveImplementation(TSqlParser::class.java)

                tsqlParser.printDrink("DELETE FROM M_Data WHERE Field1 = Value")
            }

            scenario("should find stuff in insert") {
                val tsqlParser = di.resolveImplementation(TSqlParser::class.java)

                tsqlParser.printDrink("INSERT INTO M_Data VALUES (value1, value2)")
            }

            scenario("should find stuff in insert with column names") {
                val tsqlParser = di.resolveImplementation(TSqlParser::class.java)

                tsqlParser.printDrink("INSERT INTO M_Data (Field1, Field2) VALUES (value1, value2)")
            }

            scenario("should find stuff in exec") {
                val tsqlParser = di.resolveImplementation(TSqlParser::class.java)

                tsqlParser.printDrink("EXEC sp_addlinkedserver 'SeattleSales', 'SQL Server'")
            }

            scenario("should find stuff in function") {
                val tsqlParser = di.resolveImplementation(TSqlParser::class.java)

                tsqlParser.printTree("select dbo.ScalarFunctionName(@param1)")
            }


        }
    }
}