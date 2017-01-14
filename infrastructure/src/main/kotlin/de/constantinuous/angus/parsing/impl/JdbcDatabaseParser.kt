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
        conn.catalog = "IOL_Dubai_P"
        val md = conn.metaData
        val numericFunctions = md.numericFunctions.split(",")
        val procedures = md.getProcedures(null, null, "%")
        val tables = md.getTables(null, null, "%", null)
        println("---- Procedures ----")
        while (procedures.next()) {
            println("Procedure: "+procedures.getString(3))
        }
        println("---- Functions ----")
        for (function in numericFunctions) {
            println("Numeric Function: "+function)
        }
        println("---- Tables ----")
        while (tables.next()) {
            println("Table: "+tables.getString(3))
        }
    }

    private fun getProcedureCode(connection: Connection, myViewName : String){
        val query = "exec sp_helptext ?"
        val stmt = connection.prepareStatement(query)
        stmt.setString(1, myViewName)
        val rs = stmt.executeQuery()
        val b = StringBuilder()
        while (rs.next()) {
            b.append(rs.getString("Text"))
        }
        rs.close()
        stmt.close()
        println(b.toString())
    }
}