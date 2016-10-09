package de.constantinuous.angus.parsing.impl

import de.constantinuous.angus.parsing.DatabaseParser
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * Created by RichardG on 09.10.2016.
 */
class JdbcDatabaseParser : DatabaseParser {

    override fun extractFoo(connectionString: String, user: String, password: String){

        val conn = DriverManager.getConnection(connectionString, user, password)
        val md = conn.getMetaData()
        val functions = md.getNumericFunctions()
        val procedures = md.getProcedures(null, null, "%")
        val tables = md.getTables(null, null, "%", null)
        while (tables.next()) {
            System.out.println("Table: "+tables.getString(3))
        }
    }

    private fun foo(){

    }
}