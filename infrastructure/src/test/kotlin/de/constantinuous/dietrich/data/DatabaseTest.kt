package de.constantinuous.dietrich.data

import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 14.01.2017.
 */
class DatabaseTest : FeatureSpec(){


    init {
        feature("Empty Feature") {
            scenario("Test statement insert"){
                val foo = Database(DriverManagerDataSource())

                foo.executeSql("CREATE TABLE PERSON(id int primary key, name varchar(255))")
                foo.executeSql("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')")
                foo.executeSql("INSERT INTO PERSON(id, name) VALUES(2, 'Sonia')")
                foo.executeSql("INSERT INTO PERSON(id, name) VALUES(3, 'Asha')")

                foo.executeQuery("SELECT * FROM PERSON")

                foo.executeSql("DROP ALL OBJECTS")
            }

            scenario("Test getting stored procedures"){
                val foo = Database(DriverManagerDataSource())
                foo.executeSql("""
                CREATE ALIAS QUERY AS $$
                ResultSet query(Connection conn, String sql) throws SQLException {
                  return conn.createStatement().executeQuery(sql);
                } $$;
                """)

                foo.getStoredProcedures()

                foo.call("CALL QUERY('SELECT * FROM DUAL');")

                foo.executeSql("DROP ALL OBJECTS")
            }

            scenario("Test from stored procedure query table"){
                val foo = Database(DriverManagerDataSource())
                foo.executeSql("""
                CREATE ALIAS QUERY AS $$
                ResultSet query(Connection conn, String sql) throws SQLException {
                  return conn.createStatement().executeQuery(sql);
                } $$;
                """)
                foo.executeSql("CREATE TABLE DUAL(id int primary key, name varchar(255))")
                foo.executeSql("INSERT INTO DUAL(id, name) VALUES(1, 'Anju')")
                foo.executeSql("INSERT INTO DUAL(id, name) VALUES(2, 'Sonia')")
                foo.executeSql("INSERT INTO DUAL(id, name) VALUES(3, 'Asha')")

                foo.call("CALL QUERY('SELECT * FROM DUAL');")

                foo.executeSql("DROP ALL OBJECTS")
            }
        }
    }
}