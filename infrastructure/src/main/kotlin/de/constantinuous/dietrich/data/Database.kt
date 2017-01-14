package de.constantinuous.dietrich.data

import com.mchange.v2.c3p0.ComboPooledDataSource
import de.constantinuous.angus.common.use
import java.io.Closeable
import java.io.FileInputStream
import java.sql.*
import javax.naming.Context
import javax.naming.InitialContext
import javax.sql.DataSource

/**
 * Created by RichardG on 14.01.2017.
 */
class Database (private val dataSource: DataSource) {

    @Throws(SQLException::class)
    fun getStoredProcedures() {
        val connection = getDBConnection()

        val procedures = connection.metaData.getProcedures(null, null, "%")

        while (procedures.next()) {
            println("Procedure: "+procedures.getString(3))
        }


    }


    @Throws(SQLException::class)
    fun call(sql: String) {
        val connection = getDBConnection()
        connection.prepareCall(sql).use{ cStmt ->
            var hadResults = cStmt.execute();
            while (hadResults) {
                val rs = cStmt.resultSet;

                println(rs)
                hadResults = cStmt.moreResults;
            }
        }
    }

    @Throws(SQLException::class)
    fun executeSql(sql: String) {
        getDBConnection().use { connection ->
            connection.setAutoCommit(false)
            connection.createStatement().use { statement ->
                statement.execute(sql)
            }

        }
    }

    @Throws(SQLException::class)
    fun executeQuery(sql: String) {
        getDBConnection().use { connection ->
            connection.setAutoCommit(false)
            connection.createStatement().use { statement ->
                val result = statement.executeQuery(sql)
                while (result.next()) {
                    System.out.println("Id " + result.getInt("id") + " Name " + result.getString("name"))
                }
            }

        }
    }

    private fun getDBConnection(): Connection {
        return dataSource.connection
    }

}