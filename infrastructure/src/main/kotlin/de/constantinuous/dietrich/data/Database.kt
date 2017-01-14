package de.constantinuous.dietrich.data

import de.constantinuous.angus.common.use
import java.io.Closeable
import java.io.FileInputStream
import java.sql.*

/**
 * Created by RichardG on 14.01.2017.
 */
class Database {

    private val DB_DRIVER = "org.h2.Driver"
    private val DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    private val DB_USER = ""
    private val DB_PASSWORD = ""


    @Throws(SQLException::class)
    fun getStoredProcedures() {
        val connection = getDBConnection()

        val stmt = connection.createStatement();
        stmt.execute("""
        CREATE ALIAS QUERY AS $$
        ResultSet query(Connection conn, String sql) throws SQLException {
          return conn.createStatement().executeQuery(sql);
        } $$;
        """)
        stmt.close()

        val procedures = connection.metaData.getProcedures(null, null, "%")

        while (procedures.next()) {
            println("Procedure: "+procedures.getString(3))
        }

         connection.prepareCall("CALL QUERY('SELECT * FROM DUAL');").use{ cStmt ->
             var hadResults = cStmt.execute();
             while (hadResults) {
                 val rs = cStmt.resultSet;

                 println(rs)
                 hadResults = cStmt.moreResults;
             }
         }
    }

    @Throws(SQLException::class)
    fun insertWithPreparedStatement() {
        val connection = getDBConnection()

        val CreateQuery = "CREATE TABLE PERSON(id int primary key, name varchar(255))"
        val InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)"
        val SelectQuery = "select * from PERSON"

        try {
            connection.setAutoCommit(false)

            val createPreparedStatement = connection.prepareStatement(CreateQuery)
            createPreparedStatement.executeUpdate()
            createPreparedStatement!!.close()

            val insertPreparedStatement = connection.prepareStatement(InsertQuery)
            insertPreparedStatement.setInt(1, 1)
            insertPreparedStatement.setString(2, "Jose")
            insertPreparedStatement.executeUpdate()
            insertPreparedStatement.close()

            val selectPreparedStatement = connection.prepareStatement(SelectQuery)
            val rs = selectPreparedStatement.executeQuery()
            println("H2 In-Memory Database inserted through PreparedStatement")
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"))
            }
            selectPreparedStatement.close()

            connection.commit()
        } catch (e: SQLException) {
            System.out.println("Exception Message " + e.message)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.close()
        }
    }

    @Throws(SQLException::class)
    fun insertWithStatement() {
        val connection = getDBConnection()
        try {
            connection.setAutoCommit(false)
            val stmt = connection.createStatement()
            stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))")
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')")
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(2, 'Sonia')")
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(3, 'Asha')")

            val rs = stmt.executeQuery("select * from PERSON")
            println("H2 In-Memory Database inserted through Statement")
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"))
            }

            stmt.execute("DROP TABLE PERSON")
            stmt.close()
            connection.commit()
        } catch (e: SQLException) {
            System.out.println("Exception Message " + e.message)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.close()
        }
    }

    private fun getDBConnection(): Connection {
        try {
            Class.forName(DB_DRIVER)
        } catch (e: ClassNotFoundException) {
            println(e.message)
        }

        val dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)

        return dbConnection
    }

}