package de.constantinuous.dietrich.data

import com.mchange.v2.c3p0.ComboPooledDataSource
import java.io.PrintWriter
import java.sql.Connection
import java.sql.DriverManager
import java.util.logging.Logger
import javax.sql.DataSource

/**
 * Created by RichardG on 14.01.2017.
 */
class DriverManagerDataSource(DB_DRIVER:String = "org.h2.Driver",
                              private val DB_CONNECTION:String = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                              private val DB_USER:String = "",
                              private val DB_PASSWORD:String = "") : DataSource{

    init {
        try {
            Class.forName(DB_DRIVER)
        } catch (e: ClassNotFoundException) {
            println(e.message)
        }

        val cpds = ComboPooledDataSource()
        cpds.driverClass = DB_DRIVER
        cpds.jdbcUrl = DB_CONNECTION
        cpds.user = DB_USER
        cpds.password = DB_PASSWORD
    }

    override fun getLogWriter(): PrintWriter {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLoginTimeout(seconds: Int) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getParentLogger(): Logger {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoginTimeout(): Int {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isWrapperFor(iface: Class<*>?): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> unwrap(iface: Class<T>?): T {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConnection(): Connection {
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)
    }

    override fun getConnection(username: String?, password: String?): Connection {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLogWriter(out: PrintWriter?) {
        throw UnsupportedOperationException("not implemented")
    }
}